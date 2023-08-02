package com.raufbisov.auth.exception;

public record ErrorResponse(
    int statusCode,
    String message
) {
    
}
