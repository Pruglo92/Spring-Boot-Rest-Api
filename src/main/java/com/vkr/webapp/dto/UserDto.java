package com.vkr.webapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vkr.webapp.entity.Role;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDto(
        @NotNull
        @Positive
        Long id,

        @NotBlank
        String username,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @Email
        String email,

        @NotBlank
        String status,

        @NotNull
        Role role,

        @PastOrPresent
        LocalDateTime dateOfCreated,

        @PastOrPresent
        LocalDateTime dateOfLastVisit
) {
}