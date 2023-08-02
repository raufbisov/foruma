package com.raufbisov.auth;

import lombok.Builder;

@Builder
public record RegisterRequest(
    String username,
    String email,
    String password
) {
}
