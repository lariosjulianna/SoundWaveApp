package com.example.soundwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;
import com.example.soundwave.entity.Post;


public interface PostRepository extends JpaRepository<Post, UUID> {
    Optional<Post> findByUserId(UUID userId);
}
