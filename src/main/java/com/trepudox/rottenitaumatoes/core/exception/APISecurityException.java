package com.trepudox.rottenitaumatoes.core.exception;

import lombok.Getter;

@Getter
public class APISecurityException extends RuntimeException {

    private final String title;

    private final String detail;

    private final int status;

    public APISecurityException(String title, String detail, int status) {
        super(detail);
        this.title = title;
        this.detail = detail;
        this.status = status;
    }

}
