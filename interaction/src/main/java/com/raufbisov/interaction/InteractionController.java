package com.raufbisov.interaction;

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
@RequestMapping("api/interact")
@RequiredArgsConstructor
public class InteractionController {

  private final InteractionService interactionService;

  @PostMapping
  public ResponseEntity<String> interact(
    @RequestBody InteractionRequest interactRequest,
    @RequestHeader("Authorization") String header
  ) {
    return interactionService.addInteraction(interactRequest, header);
  }

  @GetMapping("/{interaction}/{id}")
  public boolean interactionExists(
    @PathVariable("id") String postId,
    @PathVariable("interaction") String interaction,
    @RequestHeader("Authorization") String header
  ) {
    return interactionService.interactionExists(postId, interaction, header);
  }

  @DeleteMapping("/{interaction}/{id}")
  public ResponseEntity<String> uninteract(
    @PathVariable("id") String postId,
    @PathVariable("interaction") String interaction,
    @RequestHeader("Authorization") String header
  ) {
    return interactionService.deleteInteraction(postId, interaction, header);
  }
}
