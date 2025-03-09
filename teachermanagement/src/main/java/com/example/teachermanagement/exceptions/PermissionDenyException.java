package com.example.teachermanagement.exceptions;

public class PermissionDenyException extends RuntimeException {
    public PermissionDenyException(String message) {
        super(message);
    }
}
