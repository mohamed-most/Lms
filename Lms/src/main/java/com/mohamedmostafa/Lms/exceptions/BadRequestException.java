package com.mohamedmostafa.Lms.exceptions;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String msg) {
        super(msg);
    }
}
