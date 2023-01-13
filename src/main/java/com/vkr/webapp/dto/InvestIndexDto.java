package com.vkr.webapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InvestIndexDto(

        Long id,

        @NotBlank
        String investName
) {
}
