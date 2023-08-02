package com.raufbisov.interaction;

import com.raufbisov.auth.AuthClient;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InteractionService {

  private final InteractionRepository interactionRepository;
  private final AuthClient authClient;

  public ResponseEntity<String> addInteraction(
    InteractionRequest request,
    String header
  ) {
    authClient.validateToken(header);
    Interaction interaction = Interaction
      .builder()
      .userId(UUID.fromString(request.userId()))
      .contentId(UUID.fromString(request.contentId()))
      .contentType(ContentType.valueOf(request.contentType()))
      .interactionType(InteractionType.valueOf(request.interactionType()))
      .build();
    interactionRepository.save(interaction);
    return ResponseEntity.ok("Added Interaction");
  }

  public boolean interactionExists(
    String contentId,
    String interactionType,
    String header
  ) {
    authClient.validateToken(header);
    String userId = authClient.getUserId(header).getBody();
    return interactionRepository.existsByUserIdAndContentIdAndInteractionType(
      UUID.fromString(userId),
      UUID.fromString(contentId),
      InteractionType.LIKE
    );
  }

  @Transactional
  public ResponseEntity<String> deleteInteraction(
    String contentId,
    String interactionType,
    String header
  ) {
    authClient.validateToken(header);
    String userId = authClient.getUserId(header).getBody();
    interactionRepository.deleteByUserIdAndContentIdAndInteractionType(
      UUID.fromString(userId),
      UUID.fromString(contentId),
      InteractionType.valueOf(interactionType)
    );
    return ResponseEntity.ok("Deleted Interaction");
  }
}
