package com.study.restclient.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.http.client.ClientHttpRequestFactoryBuilder;
import org.springframework.boot.http.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Configuration
public class RestClientConfig {

    private static final Duration READ_TIMEOUT = Duration.ofSeconds(5);
    private static final Duration CONNECTION_TIMEOUT = Duration.ofSeconds(5);

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .requestFactory(clientHttpRequestFactory())
                .requestInterceptor(clientHttpRequestInterceptor())
                .build();
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        ClientHttpRequestFactorySettings settings = ClientHttpRequestFactorySettings.defaults()
                .withReadTimeout(READ_TIMEOUT)
                .withConnectTimeout(CONNECTION_TIMEOUT);
        ClientHttpRequestFactory requestFactory = ClientHttpRequestFactoryBuilder.detect().build(settings);
        return new BufferingClientHttpRequestFactory(requestFactory);
    }

    private ClientHttpRequestInterceptor clientHttpRequestInterceptor() {
        return (request, body, execution) -> {
            logRequest(request, body);
            try {
                ClientHttpResponse response = execution.execute(request, body);
                logResponse(response);
                return response;
            } catch (Throwable throwable) {
                throw new ExternalApiException(throwable);
            }
        };
    }

    private void logRequest(HttpRequest request, byte[] body) {
        log.info("URI: {}, Method: {}, Headers:{}, Body:{} ",
                request.getURI(), request.getMethod(), request.getHeaders(), new String(body, StandardCharsets.UTF_8));
    }

    private void logResponse(ClientHttpResponse response) throws IOException {
        String body = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));

        log.info("Status: {}, Headers:{}, Body:{} ", response.getStatusCode(), response.getHeaders(), body);
    }
}
