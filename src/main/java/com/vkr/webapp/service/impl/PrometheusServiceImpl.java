package com.vkr.webapp.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.vkr.webapp.prometheus.Prometheus;
import com.vkr.webapp.prometheus.ScrapeConfigs;
import com.vkr.webapp.prometheus.StaticConfigs;
import com.vkr.webapp.service.PrometheusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PrometheusServiceImpl implements PrometheusService {
/*    StringBuilder sb = new StringBuilder("[\"10.120.0.3:9100\"]");*/
    private static final String STRING = "10.120.0.3:9100";

    @Override
    public void addInYml() {
        List<ScrapeConfigs> scrapeConfigs = new ArrayList<>();
        var mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
        scrapeConfigs.add(new ScrapeConfigs("node_10.120.0.3", Collections.singletonList(new StaticConfigs(STRING.split("")))));
        var prometheus = new Prometheus(scrapeConfigs);
        try {
            mapper.writeValue(new File("/home/vladimir/work/lol.yml"), prometheus);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
