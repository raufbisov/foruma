package com.raufbisov.auth.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.raufbisov.auth.RegisterRequest;
import com.raufbisov.auth.exception.UserAlreadyExistsException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserCredentialService implements UserDetailsService{

    private final UserCredentialRepository userCredentialRepository;
    private final PasswordEncoder passwordEncoder;

    public UserCredential saveUser(RegisterRequest request) {
        if (userCredentialRepository.findByEmail(request.email()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }
        UserCredential user = UserCredential.builder()
            .username(request.username())
            .password(passwordEncoder.encode(request.password()))
            .email(request.email())
            .role(Role.USER)
            .build();
        return userCredentialRepository.save(user);
    }

    @Override
    public UserCredential loadUserByUsername(String username) throws UsernameNotFoundException {
        return userCredentialRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    
}
