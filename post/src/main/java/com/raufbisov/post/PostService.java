package com.raufbisov.post;

import com.raufbisov.auth.AuthClient;
import com.raufbisov.interaction.InteractionClient;
import com.raufbisov.interaction.InteractionRequest;
import com.raufbisov.interaction.InteractionType;
import com.raufbisov.post.exception.UserNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final AuthClient authClient;
  private final InteractionClient interactionClient;

  public Post getPostById(String id, String header) {
    authClient.validateToken(header);
    return postRepository
      .findById(UUID.fromString(id))
      .orElseThrow(UserNotFoundException::new);
  }

  public List<Post> getAllPosts(String header) {
    authClient.validateToken(header);
    return postRepository.findAll();
  }

  public void createPost(PostCreateRequest request, String header)
    throws ParseException {
    authClient.validateToken(header).getBody();
    String userId = authClient.getUserId(header).getBody();
    Post post = Post
      .builder()
      .userId(UUID.fromString(userId))
      .title(request.title())
      .content(request.content())
      .createdAt(
        new SimpleDateFormat("yy-mm-dd hh:mm:ss").parse(request.createdAt())
      )
      .updatedAt(
        new SimpleDateFormat("yy-mm-dd hh:mm:ss").parse(request.updatedAt())
      )
      .category(Category.valueOf(request.category()))
      .build();
    postRepository.save(post);
  }

  public void updatePost(PostUpdateRequest request, String header) {
    authClient.validateToken(header);
    Post post = postRepository
      .findById(UUID.fromString(request.id()))
      .orElseThrow(UserNotFoundException::new);
    post.setTitle(request.title());
    post.setContent(request.title());
    post.setCategory(Category.valueOf(request.category()));

    postRepository.save(post);
  }

  public void deletePost(String id, String header) {
    authClient.validateToken(header);
    postRepository.deleteById(UUID.fromString(id));
  }

  public ResponseEntity<String> likePost(String id, String header) {
    authClient.validateToken(header).getBody();
    if (
      interactionClient.interactionExists(
        id,
        InteractionType.LIKE.name(),
        header
      )
    ) {
      interactionClient.uninteract(id, InteractionType.LIKE.name(), header);
      return ResponseEntity.ok("Disliked Post");
    } else {
      String userId = authClient.getUserId(header).getBody();
      interactionClient.interact(
        new InteractionRequest(userId, id, "POST", InteractionType.LIKE.name()),
        header
      );
      return ResponseEntity.ok("Liked Post");
    }
  }
}
