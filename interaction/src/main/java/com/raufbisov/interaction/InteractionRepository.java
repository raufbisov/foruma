package com.raufbisov.interaction;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InteractionRepository extends JpaRepository<Interaction, UUID>{
    boolean existsByUserIdAndContentIdAndInteractionType(UUID userId, UUID contentId, InteractionType interactionType);
    void deleteByUserIdAndContentIdAndInteractionType(UUID userId, UUID contentId, InteractionType interactionType);
    Optional<Interaction> findByUserIdAndContentIdAndInteractionType(UUID fromString, UUID fromString2,
            InteractionType valueOf);
}
