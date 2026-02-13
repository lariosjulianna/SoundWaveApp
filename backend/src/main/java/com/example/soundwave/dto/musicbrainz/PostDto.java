package com.example.soundwave.dto.musicbrainz;

import java.time.Instant;
import java.util.UUID;

public class PostDto {

    private UUID id;
    private UUID userId;
    private Instant createdAt;
    private UUID topicId;
    private String content;

    public PostDto(UUID id, UUID userId, Instant createdAt, UUID topicId, String content) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.topicId = topicId;
        this.content = content;
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public Instant getCreatedAt() { return createdAt; }
    public UUID getTopicId() { return topicId; }
    public String getContent() { return content; }
}
