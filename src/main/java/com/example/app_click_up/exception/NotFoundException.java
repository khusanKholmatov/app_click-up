package com.example.app_click_up.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final String search;
    public NotFoundException(String message, String search) {
        super(message);
        this.search = search;
    }
}
