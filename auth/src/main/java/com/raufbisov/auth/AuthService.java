package com.raufbisov.auth;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.raufbisov.auth.jwt.JwtService;
import com.raufbisov.auth.user.Role;
import com.raufbisov.auth.user.UserCredential;
import com.raufbisov.auth.user.UserCredentialService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserCredentialService userCredentialService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        UserCredential user = userCredentialService.saveUser(request);
        String jwt = jwtService.generateToken(user);
        return AuthResponse.builder()
            .token(jwt)
            .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password(), List.of(new SimpleGrantedAuthority(Role.USER.name()))));
        UserCredential user = userCredentialService.loadUserByUsername(request.username());
        String jwt = jwtService.generateToken(user);
        return AuthResponse.builder()
            .token(jwt)
            .build();
    }

    public String getUserIdFromToken(String header) {
        String token = header.substring(7);
        String username = jwtService.extractUsername(token);
        UserCredential userCredential = userCredentialService.loadUserByUsername(username);
        return userCredential.getId().toString();
    }

    public ValidateTokenResponse validateToken(String header) {
        String token = header.substring(7);
        String username = jwtService.extractUsername(token);
        UserCredential userCredential = userCredentialService.loadUserByUsername(username);
        boolean isValid = jwtService.isTokenValid(token, userCredential);
       return ValidateTokenResponse.builder()
            .id(userCredential.getId())
            .username(username)
            .isValid(isValid)
            .build();
    }
}
