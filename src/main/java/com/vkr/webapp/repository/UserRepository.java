package com.vkr.webapp.repository;

import com.vkr.webapp.entity.User;
import com.vkr.webapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<User> findAllByRole(Role role);

    List<User> findAllByRoleId(Long roleId);
}
