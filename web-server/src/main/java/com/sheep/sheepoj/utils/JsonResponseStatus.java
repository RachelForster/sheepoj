package com.sheep.sheepoj.utils;

import org.springframework.security.core.parameters.P;

public enum JsonResponseStatus {
    SUCCESS(0),
    UNKNOWN_ERROR(1),
    TOKEN_ERROR(110),
    ACCESS_TOKEN_EXPIRED(111),
    ACCESS_DENIED(10009);

    private int code;
    JsonResponseStatus(int code){this.code=code;}
    public int getCode()
    {
        return this.code;
    }
}
