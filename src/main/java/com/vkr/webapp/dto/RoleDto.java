package com.vkr.webapp.dto;

import com.vkr.webapp.entity.enums.Status;

import javax.validation.constraints.NotBlank;

public record RoleDto(

        @NotBlank
        Long id,

        @NotBlank
        String name,

        @NotBlank
        Status status

/*        @JsonInclude(JsonInclude.Include.NON_NULL)
        UserDto user*/
) {
}
