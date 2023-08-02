package com.raufbisov.comment;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, UUID>{
    List<Comment> findByPostId(UUID postId);
}
