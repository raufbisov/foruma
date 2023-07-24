package com.raufbisov.auth;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserCredential {
    @SequenceGenerator(
        name = "user_id_sequence",
        sequenceName = "user_id_sequence"
    )
    @GeneratedValue(
        strategy = GenerationType.UUID,
        generator = "user_id_sequence"
    )
    private UUID id;
    private String username;
    private String password;
    private String email;
}
