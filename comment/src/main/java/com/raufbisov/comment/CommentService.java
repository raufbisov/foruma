package com.raufbisov.comment;

import com.raufbisov.auth.AuthClient;
import com.raufbisov.interaction.InteractionClient;
import com.raufbisov.interaction.InteractionRequest;
import com.raufbisov.interaction.InteractionType;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final AuthClient authClient;
  private final InteractionClient interactionClient;

  public List<Comment> getPostsAllComments(String postId, String header) {
    authClient.validateToken(header);
    return commentRepository.findByPostId(UUID.fromString(postId));
  }

  public ResponseEntity<String> createComment(
    CreateCommentRequest request,
    String header
  ) {
    authClient.validateToken(header).getBody();
    String userId = authClient.getUserId(header).getBody();
    Comment comment = Comment
      .builder()
      .content(request.content())
      .postId(UUID.fromString(request.postId()))
      .userId(UUID.fromString(userId))
      .build();
    commentRepository.save(comment);
    return ResponseEntity.ok("Comment posted");
  }

  public ResponseEntity<String> deleteComment(String commentId, String header) {
    authClient.validateToken(header);
    commentRepository.deleteById(UUID.fromString(commentId));
    return ResponseEntity.ok("Deleted comment");
  }

  @Transactional
  public ResponseEntity<String> likeComment(String id, String header) {
    authClient.validateToken(header).getBody();
    if (
      interactionClient.interactionExists(
        id,
        InteractionType.LIKE.name(),
        header
      )
    ) {
      interactionClient.uninteract(id, InteractionType.LIKE.name(), header);
      return ResponseEntity.ok("Disliked comment");
    } else {
      String userId = authClient.getUserId(header).getBody();
      interactionClient.interact(
        new InteractionRequest(userId, id, "COMMENT", "LIKE"),
        header
      );
      return ResponseEntity.ok("Liked comment");
    }
  }

  @Transactional
  public ResponseEntity<String> saveComment(String id, String header) {
    authClient.validateToken(header);
    if (
      interactionClient.interactionExists(
        id,
        InteractionType.SAVE.name(),
        header
      )
    ) {
      interactionClient.uninteract(id, InteractionType.SAVE.name(), header);
      return ResponseEntity.ok("Unsaved comment");
    } else {
      String userId = authClient.getUserId(header).getBody();
      interactionClient.interact(
        new InteractionRequest(
          userId,
          id,
          "COMMENT",
          InteractionType.SAVE.name()
        ),
        header
      );
      return ResponseEntity.ok("Saved comment");
    }
  }
}
