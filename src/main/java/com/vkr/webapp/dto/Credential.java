package com.vkr.webapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Credential {
    @Schema(defaultValue = "test_username_user")
    @JsonProperty("username")
    private String username;

    @Schema(defaultValue = "test_password_user")
    @JsonProperty("password")
    private String password;
}
