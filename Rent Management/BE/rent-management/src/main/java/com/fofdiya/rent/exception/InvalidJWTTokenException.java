package com.fofdiya.rent.exception;

public class InvalidJWTTokenException extends RuntimeException {

    public InvalidJWTTokenException() {
        super("Invalid JWT token");
    }
}
