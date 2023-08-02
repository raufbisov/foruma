package com.raufbisov.post;

public record PostCreateRequest(
    String title,
    String content,
    String createdAt,
    String updatedAt,
    String category
) {
    
}
