package com.tabletennis.infrastructure.http.common;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@UtilityClass
public class RestClientBuilderFactory {

    public RestClient restClient(
            int readTimeoutMillis,
            int connectTimeoutMillis,
            String host,
            Map<String, String> defaultHeaders,
            String serviceName
    ) {
        var service = PoolingHttpClientConnectionManagerBuilder.create()
                .setDefaultConnectionConfig(
                        ConnectionConfig.custom()
                                .setSocketTimeout(readTimeoutMillis, TimeUnit.MILLISECONDS)
                                .setConnectTimeout(connectTimeoutMillis, TimeUnit.MILLISECONDS)
                                .build()
                ).build();

        var httpClient = HttpClientBuilder.create()
                .setConnectionManager(service)
                .build();

        var requestFactory = new BufferingClientHttpRequestFactory(
                new HttpComponentsClientHttpRequestFactory(httpClient)
        );

        var restClient = RestClient.builder().requestFactory(requestFactory)
                .baseUrl(host)
                .defaultHeaders(headers -> {
                    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    defaultHeaders.forEach(headers::add);
                })
                .requestInterceptor((request, body, execution) -> {
                    String requestBody = new String(body, StandardCharsets.UTF_8);
                    if (requestBody.isEmpty()) {
                        requestBody = "{}";
                    }
                    log.info("\n\n[{} API Request]\nMethod      : {}\nURI         : {}\nHeaders     : {}\nBody        : {}",
                            serviceName, request.getMethod(), request.getURI(), request.getHeaders(), requestBody);

                    return execution.execute(request, body);
                })
                .defaultStatusHandler(HttpStatusCode::is2xxSuccessful, (request, response) -> {
                    String responseBody = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);
                    log.info("\n\n[{} API 2xx Success Response]\nRequest URI         : {}\nResponse Status     : {}\nResponse Body       : {}",
                            serviceName, request.getURI(), response.getStatusCode(), responseBody);
                })
                .defaultStatusHandler(HttpStatusCode::is4xxClientError, (request, response) -> {
                    String responseBody = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);
                    log.warn("\n\n[{} API 4xx Client Error Response]\nRequest URI         : {}\nResponse Status     : {}\nResponse Body       : {}",
                            serviceName, request.getURI(), response.getStatusCode(), responseBody);
                })
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, (request, response) -> {
                    String responseBody = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);
                    log.error("\n\n[{} API 5xx Server Error Response]\nRequest URI         : {}\nResponse Status     : {}\nResponse Body       : {}",
                            serviceName, request.getURI(), response.getStatusCode(), responseBody);
                })
                .build();

        return restClient;
    }
}
