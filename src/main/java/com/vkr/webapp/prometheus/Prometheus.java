package com.vkr.webapp.prometheus;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Prometheus(
        @JsonProperty("scrape_configs")
        List<ScrapeConfigs> scrapeConfigsList
) {
}
