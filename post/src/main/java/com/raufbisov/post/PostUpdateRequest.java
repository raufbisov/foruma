package com.raufbisov.post;

public record PostUpdateRequest(
    String id,
    String title,
    String content,
    String category
) {
    
}
