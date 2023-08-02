package com.raufbisov.post.exception;

public record ErrorResponse(
    int statusCode,
    String message
) {
}