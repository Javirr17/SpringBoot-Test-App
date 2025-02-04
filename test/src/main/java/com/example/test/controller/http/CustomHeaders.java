package com.example.test.controller.http;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomHeaders {
    /** Identificador único de la petición */
    public static final String X_REQUEST_ID = "X-Request-ID";
}

