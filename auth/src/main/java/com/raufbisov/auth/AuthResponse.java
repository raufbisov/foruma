package com.raufbisov.auth;

import lombok.Builder;

@Builder
public record AuthResponse(String token) {
  
}
