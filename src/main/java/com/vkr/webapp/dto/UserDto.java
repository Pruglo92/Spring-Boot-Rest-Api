package com.vkr.webapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDto(

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long id,

        @Schema(defaultValue = "test_username_user1")
        @NotBlank
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String username,

        @Schema(defaultValue = "test_password_user1")
        @NotBlank
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String password,

        @Schema(defaultValue = "boris")
        @NotBlank
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String firstName,

        @Schema(defaultValue = "borisov")
        @NotBlank
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String lastName,

        @Schema(defaultValue = "boris@rulit.ru")
        @Email
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String email,

        @Schema(defaultValue = "NOT_ACTIVE")
        @NotBlank
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String status,

        @Schema
        @NotNull
        @JsonInclude(JsonInclude.Include.NON_NULL)
        RoleDto role,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        LocalDateTime dateOfCreated,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        LocalDateTime dateOfLastVisit
) {
}