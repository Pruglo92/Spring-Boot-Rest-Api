package com.vkr.webapp.mapper;

import com.vkr.webapp.dto.RoleDto;
import com.vkr.webapp.entity.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {
    RoleDto toDto(Role role);
}
