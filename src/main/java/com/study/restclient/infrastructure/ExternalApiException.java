package com.study.restclient.infrastructure;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;

public class ExternalApiException extends RuntimeException {

    public ExternalApiException(Throwable throwable) {
        super(throwable);
    }

    public ExternalApiException(String message) {
        super(message);
    }
}
