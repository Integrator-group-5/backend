package com.digitalhouse.backend.exception;

public class EntityAlreadyExistsException extends Exception {
    public EntityAlreadyExistsException(String object, String type, Object value) {
        super(object + " already exists: (" + type + ": " + value + ")");
    }
}