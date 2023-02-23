package com.vkr.webapp.mapper;

import com.vkr.webapp.dto.UserDto;
import com.vkr.webapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper/*(uses = {RoleMapper.class})*/
public interface UserMapper {
    UserDto toDto(User entity);

    User toEntity(UserDto dto);

    @Mapping(target = "id", ignore = true)
    User update(@MappingTarget User entity, UserDto dto);
}
