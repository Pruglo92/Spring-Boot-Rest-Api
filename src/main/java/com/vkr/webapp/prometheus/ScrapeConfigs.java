package com.vkr.webapp.prometheus;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ScrapeConfigs(

        @JsonProperty("job_name")
        String jobName,

        @JsonProperty("static_configs")
        List<StaticConfigs> static_configs
) {
}
