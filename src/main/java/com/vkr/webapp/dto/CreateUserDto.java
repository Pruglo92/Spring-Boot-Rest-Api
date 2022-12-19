package com.vkr.webapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vkr.webapp.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateUserDto(

        @Schema(defaultValue = "test_username_user1")
        @NotBlank
        String username,

        @Schema(defaultValue = "test_password_user2")
        @NotBlank
        String password,

        @Schema(defaultValue = "boris")
        @NotBlank
        String firstName,

        @Schema(defaultValue = "borisov")
        @NotBlank
        String lastName,

        @Schema(defaultValue = "boris@rulit.ru")
        @Email
        String email,

        @NotNull
        Role role
) {
}
