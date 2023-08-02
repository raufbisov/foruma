package com.raufbisov.auth;

import java.util.UUID;

import lombok.Builder;

@Builder
public record ValidateTokenResponse(
    UUID id,
    String username,
    boolean isValid
) {
    
}
