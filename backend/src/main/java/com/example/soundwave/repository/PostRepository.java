package com.example.soundwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
import com.example.soundwave.entity.Post;


public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByUserId(UUID userId);
}
