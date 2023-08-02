package com.raufbisov.comment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentNotFoundException extends RuntimeException {
    private String message;    
}
