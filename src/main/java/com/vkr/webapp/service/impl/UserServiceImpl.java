package com.vkr.webapp.service.impl;

import com.vkr.webapp.dto.UserDto;
import com.vkr.webapp.entity.Role;
import com.vkr.webapp.entity.User;
import com.vkr.webapp.exception.UserCreateException;
import com.vkr.webapp.exception.UserNotFoundException;
import com.vkr.webapp.exception.UserSaveException;
import com.vkr.webapp.mapper.UserMapper;
import com.vkr.webapp.repository.UserRepository;
import com.vkr.webapp.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public User register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserCreateException("user with username " + user.getUsername() + " already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserCreateException("user with email " + user.getUsername() + " already exists");
        }
        var savedUser = userRepository.save(user);
        log.info("was registered " + user);
        return savedUser;
    }

    @Override
    public List<User> getAll() {
        var users = userRepository.findAll();
        log.info("user repository has {} entries", users.size());
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("was deleted user with id: {}", id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public User save(User user) {
        User result = userRepository.save(user);
        log.info("user was saved with id: {}", user.getId());
        return result;
    }

    @Override
    @Transactional
    public UserDto updateUser(User entity, UserDto dto) {
        var result = userMapper.update(entity, dto);
        log.info("user id {} was updated", entity.getId());
        return userMapper.toDto(result);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (isNull(authentication)) {
            throw new UserNotFoundException("Authentication context is empty. Need to authorize");
        }
        String currentPrincipalName = authentication.getName();
        var currentUser = this.findByUsername(currentPrincipalName);
        if (isNull(currentUser)) {
            throw new UserNotFoundException("User was not found. Need to authorize");
        }
        return currentUser;
    }

    @Override
    public void checkRights(final User user, final String needRole) {
        if (!hasRole(user, needRole)) {
            throw new UserNotFoundException("user does not has need rights");
        }
    }

    @Override
    public void checkRightsOfCurrentUser(final String needRole) {
        final var currentUser = this.getCurrentUser();
        if (!hasRole(currentUser, needRole)) {
            throw new UserNotFoundException("Current user does not has rights to process this request");
        }
    }

    @Override
    public List<User> findAllByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public List<User> getUsersByRoleId(Long roleId) {
        return userRepository.findAllByRoleId(roleId);
    }

    @Override
    @Transactional
    public User addRole(User user, Role role) {
        if (user.getRole().equals(role)) {
            throw new UserSaveException("user id " + user.getId() + " already has role " + role.getName());
        }
        user.setRole(role);
        var savedUser = userRepository.save(user);
        log.info("role {} was added to user id {}", role.getName(), user.getId());
        return savedUser;
    }

//    @Override
//    @Transactional
//    public User removeRole(User user, final Long roleId) {
//        if (!(user.getRole().getId().equals(roleId))) {
//            throw new UserSaveException("user id " + user.getId() + " does not have this role");
//        }
//        user.setRole(null);
//        var savedUser = userRepository.save(user);
//        log.info("role id {} was removed from user id {}", roleId, user.getId());
//        return savedUser;
//    }

    @Override
    public boolean hasRole(final User user, final String role) {
        return user.getRole().getName().equalsIgnoreCase(role);
    }
}
