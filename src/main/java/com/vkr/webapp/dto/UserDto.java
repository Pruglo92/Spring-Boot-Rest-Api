package com.vkr.webapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vkr.webapp.entity.Role;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDto(

        @NotNull
        @Positive
        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long id,

        @Schema(defaultValue = "test_username_user1")
        @NotBlank
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String username,

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
        Role role,

        @PastOrPresent
        @JsonInclude(JsonInclude.Include.NON_NULL)
        LocalDateTime dateOfCreated,

        @PastOrPresent
        @JsonInclude(JsonInclude.Include.NON_NULL)
        LocalDateTime dateOfLastVisit
) {
}