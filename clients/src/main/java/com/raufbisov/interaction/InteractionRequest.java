package com.raufbisov.interaction;

public record InteractionRequest(
    String userId,
    String contentId,
    String contentType,
    String interactionType
) {
    
}
