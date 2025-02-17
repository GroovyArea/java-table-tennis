package com.tabletennis.infrastructure.http.faker;

import com.tabletennis.infrastructure.http.common.RestClientBuilderFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.HashMap;

@Configuration
@ConditionalOnProperty(
        prefix = "table-tennis.infra.http.faker-api",
        name = {"enabled"},
        havingValue = "true",
        matchIfMissing = false
)
@EnableConfigurationProperties(FakerApiProperties.class)
public class FakerClientConfiguration {

    private static final String SERVICE_NAME = "Faker";
    private static final int READ_TIMEOUT_MILLIS = 5000;
    private static final int CONNECT_TIMEOUT_MILLIS = 3000;

    private final RestClient restClient;

    public FakerClientConfiguration(FakerApiProperties properties) {
        this.restClient = RestClientBuilderFactory.restClient(
                READ_TIMEOUT_MILLIS,
                CONNECT_TIMEOUT_MILLIS,
                properties.getHost(),
                new HashMap<>(),
                SERVICE_NAME
        );
    }

    @Bean
    public HttpServiceProxyFactory fakerFactory() {
        return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
    }

    @Bean
    @ConditionalOnMissingBean
    public FakerClient fakerClient() {
        return fakerFactory().createClient(FakerClient.class);
    }
}
