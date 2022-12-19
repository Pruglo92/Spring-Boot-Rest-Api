package com.vkr.webapp.service;

import com.vkr.webapp.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String name);

    List<Role> getRoles();

    Optional<Role> findById(Long roleId);
}
