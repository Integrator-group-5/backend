package com.digitalhouse.backend.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String object, String type, Object value) {
        super(object + " not found: (" + type + ": " + value + ")");
    }
}