package com.raufbisov.comment;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

  private final CommentService commentService;

  @GetMapping("{postId}")
  public List<Comment> getPostsAllComments(
    @PathVariable("postId") String postId,
    @RequestHeader("Authorization") String header
  ) {
    return commentService.getPostsAllComments(postId, header);
  }

  @PostMapping
  public ResponseEntity<String> createComment(
    @RequestBody CreateCommentRequest request,
    @RequestHeader("Authorization") String header
  ) {
    return commentService.createComment(request, header);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<String> deleteComment(
    @PathVariable("id") String id,
    @RequestHeader("Authorization") String header
  ) {
    return commentService.deleteComment(id, header);
  }

  @GetMapping("/{id}/like")
  public ResponseEntity<String> likeComment(
    @PathVariable("id") String id,
    @RequestHeader("Authorization") String header
  ) {
    return commentService.likeComment(id, header);
  }

  @GetMapping("/{id}/save")
  public ResponseEntity<String> saveComment(
    @PathVariable("id") String id,
    @RequestHeader("Authorization") String header
  ) {
    return commentService.saveComment(id, header);
  }
}
