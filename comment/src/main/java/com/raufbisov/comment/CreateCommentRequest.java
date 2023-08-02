package com.raufbisov.comment;

public record CreateCommentRequest(
    String content,
    String postId
) {
    
}
