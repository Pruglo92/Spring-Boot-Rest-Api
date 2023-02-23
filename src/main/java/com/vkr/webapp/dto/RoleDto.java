package com.vkr.webapp.dto;

import com.vkr.webapp.entity.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public record RoleDto(

        @NotBlank
        @Schema(defaultValue = "2")
        Long id,

        @NotBlank
        @Schema(defaultValue = "ROLE_USER")
        String name,

        @NotBlank
        @Schema(defaultValue = "ACTIVE")
        Status status

/*        @JsonInclude(JsonInclude.Include.NON_NULL)
        UserDto user*/
) {
}
