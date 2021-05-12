package com.parsdeveloper.shopping.model.commons.exception;

public class UserNotFoundException extends RuntimeException {
    private static String message = "anonymous user or something is wrong with user";

    public UserNotFoundException() {
        super(message);
    }
}

