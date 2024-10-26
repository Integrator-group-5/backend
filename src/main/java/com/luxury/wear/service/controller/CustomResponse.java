package com.luxury.wear.service.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomResponse {
    private final Boolean ok;
    private final String message;
    private final Object data;
}