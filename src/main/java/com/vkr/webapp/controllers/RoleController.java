package com.vkr.webapp.controllers;


import com.vkr.webapp.dto.RoleDto;
import com.vkr.webapp.dto.UserDto;
import com.vkr.webapp.exception.RoleNotFoundException;
import com.vkr.webapp.exception.UserNotFoundException;
import com.vkr.webapp.mapper.RoleMapper;
import com.vkr.webapp.mapper.UserMapper;
import com.vkr.webapp.service.RoleService;
import com.vkr.webapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/admin")
@Transactional(readOnly = true)
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class RoleController {

    private final UserService userService;
    private final UserMapper userMapper;

    private final RoleService roleService;
    private final RoleMapper roleMapper;


    @Operation(summary = "Get role list",
            security = {@SecurityRequirement(name = "JwtTokenAuth")})
    @GetMapping("/role")
    public ResponseEntity<List<RoleDto>> getRoles() {

        var resultList = roleService.getRoles().stream()
                .map(roleMapper::toDto)
                .toList();
        return ResponseEntity.ok(resultList);
    }

    @Operation(summary = "Get user list by role id",
            security = {@SecurityRequirement(name = "JwtTokenAuth")})
    @GetMapping("/role/{roleId}/user")
    public ResponseEntity<List<UserDto>> getUsersByRole(
            @Parameter(in = ParameterIn.PATH,
                    description = "id of role",
                    required = true,
                    schema = @Schema()) @PathVariable("roleId") final Long roleId) {

        var resultList = userService.getUsersByRoleId(roleId).stream()
                .map(userMapper::toDto)
                .toList();
        return ResponseEntity.ok(resultList);
    }

    @Operation(summary = "Add role to user",
            security = {@SecurityRequirement(name = "JwtTokenAuth")})
    @Transactional
    @PatchMapping("/role/{userId}/add")
    public ResponseEntity<UserDto> addRoleToUser(
            @Parameter(in = ParameterIn.PATH, description = "id of user",
                    required = true, schema = @Schema()) @PathVariable("userId") final Long userId,
            @Parameter(in = ParameterIn.DEFAULT, required = true,
                    schema = @Schema()) @RequestParam("roleId") final Long roleId) {

        var roleById = roleService.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("role id " + roleId + " was not found"));
        var userById = userService.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User id " + userId + " was not found"));

        var savedUser = userService.addRole(userById, roleById);
        var result = userMapper.toDto(savedUser);

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Remove role from user",
            security = {@SecurityRequirement(name = "JwtTokenAuth")})
    @Transactional
    @PatchMapping("/role/{userId}/remove")
    public ResponseEntity<UserDto> removeRoleFromUser(
            @Parameter(in = ParameterIn.PATH,
                    description = "id of user",
                    required = true,
                    schema = @Schema()) @PathVariable("userId") final Long userId,
            @Parameter(in = ParameterIn.DEFAULT,
                    description = "id of role",
                    required = true,
                    schema = @Schema()) @RequestParam("roleId") final Long roleId) {

        var userById = userService.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User id " + userId + " was not found"));

        var savedUser = userService.removeRole(userById, roleId);
        var result = userMapper.toDto(savedUser);

        return ResponseEntity.ok(result);
    }
}
