package com.raufbisov.interaction;

public record InteractionResponse(
    String userId,
    String contentId,
    String contentType,
    String interactionType
) {
    
}
