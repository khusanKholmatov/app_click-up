package com.example.app_click_up.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    private final String field;

    public BadRequestException(String message, String field) {
        super(message);
        this.field = field;
    }
}