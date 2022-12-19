package com.vkr.webapp.mapper;

import com.vkr.webapp.dto.CreateUserDto;
import com.vkr.webapp.dto.UpdateUserDto;
import com.vkr.webapp.dto.UserDto;
import com.vkr.webapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(CreateUserDto createUserDto);

    User toEntity(UpdateUserDto updateUserDto);

    User update(@MappingTarget User targetUser, User sourceUser);
}
