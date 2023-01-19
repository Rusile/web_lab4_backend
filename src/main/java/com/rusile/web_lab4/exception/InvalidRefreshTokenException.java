package com.rusile.web_lab4.exception;

public class InvalidRefreshTokenException extends RuntimeException {
    public InvalidRefreshTokenException(String s) {
        super(s);
    }
}
