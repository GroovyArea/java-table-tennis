package com.tabletennis.infrastructure.http.faker;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "table-tennis.infra.http.faker-api")
public class FakerApiProperties {
    private boolean enabled;
    private String host;
}
