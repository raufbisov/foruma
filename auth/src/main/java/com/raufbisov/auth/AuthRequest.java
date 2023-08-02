package com.raufbisov.auth;

import lombok.Builder;

@Builder
public record AuthRequest(
    String username,
    String password
) {
}
