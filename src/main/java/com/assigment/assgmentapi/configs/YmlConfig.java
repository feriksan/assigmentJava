package com.assigment.assgmentapi.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Data
public class YmlConfig {
    private String name;
    private String environment;
    private boolean enabled;
    private List<String> servers;
}
