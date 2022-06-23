package com.trepudox.rottenitaumatoes.core.exception;

import lombok.Getter;

@Getter
public class APIException extends RuntimeException {

    private final String title;
    private final String detail;
    private final int statusCode;

    public APIException(String title, String detail, int statusCode) {
        super(detail);
        this.title = title;
        this.detail = detail;
        this.statusCode = statusCode;
    }

}
