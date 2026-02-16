package com.example.soundwave.dto.musicbrainz;

import java.time.Instant;
import java.util.UUID;

public class PostDto {

    private final UUID id;
    private final UUID userId;
    private final UUID topicId;
    private final String content;
    private final Instant createdAt;


    /**
     * Constructor
     */

    public PostDto(UUID id, UUID userId, Instant createdAt, UUID topicId, String content) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.topicId = topicId;
        this.content = content;
    }


    /**
     * Getters
     */

    public UUID getId() { return id; }

    public UUID getUserId() { return userId; }

    public Instant getCreatedAt() { return createdAt; }

    public UUID getTopicId() { return topicId; }

    public String getContent() { return content; }
}
