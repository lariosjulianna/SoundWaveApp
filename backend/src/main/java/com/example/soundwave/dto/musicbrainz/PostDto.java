package com.example.soundwave.dto.musicbrainz;

import java.time.Instant;
import java.util.UUID;

public class PostDto {

    private final UUID id;
    private final String username;
    private final UUID topicId;
    private final String topicType;
    private final String topicName;
    private final String content;
    private final Instant createdAt;

    public PostDto(
            UUID id,
            String username,
            UUID topicId,
            String topicType,
            String topicName,
            String content,
            Instant createdAt
    ) {
        this.id = id;
        this.username = username;
        this.topicId = topicId;
        this.topicType = topicType;
        this.topicName = topicName;
        this.content = content;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }

    public String getUsername() { return username; }

    public UUID getTopicId() { return topicId; }

    public String getTopicType() { return topicType; }

    public String getTopicName() { return topicName; }

    public String getContent() { return content; }

    public Instant getCreatedAt() { return createdAt; }
}

//package com.example.soundwave.dto.musicbrainz;
//
//import java.time.Instant;
//import java.util.UUID;
//
//public class PostDto {
//
//    private final UUID id;
//    private final UUID userId;
//    private final UUID topicId;
//    private final String content;
//    private final Instant createdAt;
//
//
//    /**
//     * Constructor
//     */
//
//    public PostDto(UUID id, UUID userId, Instant createdAt, UUID topicId, String content) {
//        this.id = id;
//        this.userId = userId;
//        this.createdAt = createdAt;
//        this.topicId = topicId;
//        this.content = content;
//    }
//
//
//    /**
//     * Getters
//     */
//
//    public UUID getId() { return id; }
//
//    public UUID getUserId() { return userId; }
//
//    public Instant getCreatedAt() { return createdAt; }
//
//    public UUID getTopicId() { return topicId; }
//
//    public String getContent() { return content; }
//}
