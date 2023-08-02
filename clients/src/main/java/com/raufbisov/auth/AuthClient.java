package com.raufbisov.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("auth")
public interface AuthClient {
    @GetMapping(path = "api/auth/id")
    public ResponseEntity<String> getUserId(@RequestHeader("Authorization") String token);

    @GetMapping(path = "api/auth/validate-token")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String header);
}
