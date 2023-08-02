package com.raufbisov.interaction;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("interaction")
public interface InteractionClient {
    @PostMapping(path = "api/interact")
  public ResponseEntity<String> interact(
    @RequestBody InteractionRequest interactRequest,
    @RequestHeader("Authorization") String header
  );

  @GetMapping("api/interact/{interaction}/{id}")
  public boolean interactionExists(
    @PathVariable("id") String id,
    @PathVariable("interaction") String interaction,
    @RequestHeader("Authorization") String header
  );

  @DeleteMapping(path = "api/interact/{interaction}/{id}")
  public ResponseEntity<String> uninteract(
    @PathVariable("id") String id,
    @PathVariable("interaction") String interaction,
    @RequestHeader("Authorization") String header
  );
}
