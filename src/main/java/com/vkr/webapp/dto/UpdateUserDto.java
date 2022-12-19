package com.vkr.webapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vkr.webapp.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UpdateUserDto(

        @Schema(defaultValue = "test_username_user1New")
        @Nullable
        String username,

        @Schema(defaultValue = "test_password_user2New")
        @Nullable
        String password,

        @Schema(defaultValue = "borisNew")
        @Nullable
        String firstName,

        @Schema(defaultValue = "borisovNew")
        @Nullable
        String lastName,

        @Schema(defaultValue = "NOT_ACTIVE")
        @Nullable
        String status,

        @Schema(defaultValue = "borisNew@rulit.ru")
        @Email
        @Nullable
        String email,

        @Schema
        @Nullable
        Role roles
) {
}
