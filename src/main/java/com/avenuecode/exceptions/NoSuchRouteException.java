package com.avenuecode.exceptions;

public class NoSuchRouteException extends RuntimeException {
    public NoSuchRouteException(String message) {
        super(message);
    }
}
