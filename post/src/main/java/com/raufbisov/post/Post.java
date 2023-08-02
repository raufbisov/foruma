package com.raufbisov.post;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @UuidGenerator
    private UUID id;
    private UUID userId;
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    @Enumerated(EnumType.STRING)
    private Category category;
}
