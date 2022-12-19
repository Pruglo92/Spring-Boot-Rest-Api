package com.vkr.webapp.service;

import com.vkr.webapp.entity.User;
import com.vkr.webapp.entity.Role;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User register(User user);

    List<User> getAll();

    Optional<User> findById(Long id);

    void delete(Long id);

    User findByUsername(String username);

    User save(User user);

    User updateUser(User targetUser, User sourceUser);

    User getCurrentUser();

    void checkRights(User user, String role);

    void checkRightsOfCurrentUser(String role);

    List<User> findAllByRole(Role role);

    List<User> getUsersByRoleId(Long roleId);

    User addRole(User user, Role role);

    User removeRole(User user, Long roleId);

    User removeVmGroup(User user, Long vmGroupId);

    boolean hasRole(User user, String role);
}
