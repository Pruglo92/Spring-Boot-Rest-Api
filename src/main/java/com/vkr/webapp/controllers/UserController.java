package com.vkr.webapp.controllers;

import com.vkr.webapp.dto.UserDto;
import com.vkr.webapp.exception.UserNotFoundException;
import com.vkr.webapp.mapper.UserMapper;
import com.vkr.webapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/admin")
@Transactional(readOnly = true)
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Get user list",
            security = {@SecurityRequirement(name = "JwtTokenAuth")})
    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getUsers() {

        var resultList = userService.getAll().stream()
                .map(userMapper::toDto)
                .toList();

        return ResponseEntity.ok(resultList);
    }

    @Operation(summary = "Get user by user Id",
            security = {@SecurityRequirement(name = "JwtTokenAuth")})
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUserById(
            @Parameter(in = ParameterIn.PATH,
                    description = "id of user",
                    required = true,
                    schema = @Schema()) @PathVariable("userId") final Long userId) {

        final var currentUser = userService.getCurrentUser();
        final var result = userService.findById(userId)
                .filter(user -> !user.getId().equals(currentUser.getId()))
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User id " + userId + " was not found"));
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Create user",
            description = "This can only be done by the logged in user. Use ROLE_USER or/and ROLE_ADMIN instead of \"string\"",
            security = {@SecurityRequirement(name = "JwtTokenAuth")})
    @Transactional
    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(
            @Parameter(in = ParameterIn.DEFAULT,
                    required = true,
                    schema = @Schema()) @Valid @RequestBody UserDto userDto) {

        var user = userMapper.toEntity(userDto);
        var registeredUser = userService.register(user);
        var result = userMapper.toDto(registeredUser);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Update user. Only required fields can be specified",
            description = "This can only be done by the logged in user. Use ROLE_USER or/and ROLE_ADMIN instead of \"string\"",
            security = {@SecurityRequirement(name = "JwtTokenAuth")})
    @Transactional
    @PutMapping("/user/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @Parameter(in = ParameterIn.PATH, description = "id of user to update",
                    required = true,
                    schema = @Schema()) @PathVariable("userId") final Long userId,
            @Parameter(in = ParameterIn.DEFAULT,
                    required = true,
                    schema = @Schema()) @Valid @RequestBody UserDto userDto) {

        final var currentUser = userService.getCurrentUser();
        var targetUser = userService.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User id " + userId + " was not found"));
        var sourceUser = userMapper.toEntity(userDto);
        var updatedUser = userService.updateUser(targetUser, sourceUser);

        var result = userMapper.toDto(updatedUser);

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Delete user",
            description = "This can only be done by the logged in user.",
            security = {@SecurityRequirement(name = "JwtTokenAuth")})
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    @DeleteMapping("user/{userId}")
    public void deleteUser(
            @Parameter(in = ParameterIn.PATH, description = "id of user to delete",
                    required = true,
                    schema = @Schema()) @PathVariable("userId") final Long userId) {

        final var currentUser = userService.getCurrentUser();
        var user = userService.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User id " + userId + " was not found"));

        userService.delete(user.getId());
    }
}
