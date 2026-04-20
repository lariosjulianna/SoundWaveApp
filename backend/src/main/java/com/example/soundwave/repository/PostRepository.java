package com.example.soundwave.repository;

import com.example.soundwave.entity.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findByUser_Id(UUID userId);

    @EntityGraph(attributePaths = "user")
    List<Post> findTop50ByOrderByCreatedAtDesc();

    @EntityGraph(attributePaths = "user")
    List<Post> findByContentContainingIgnoreCase(String substring);

    @Query("SELECT p FROM Post p JOIN FETCH p.user WHERE p.id = :id")
    Optional<Post> findFetchedById(@Param("id") UUID id);
}
