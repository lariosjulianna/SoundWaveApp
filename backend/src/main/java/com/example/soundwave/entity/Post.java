package com.example.soundwave.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private UUID topicId;

    @Column(nullable = false, length = 1000)
    private String content;

    // ! finish
    /**
     * Enumerated types allow...
     */

//    private UUID topicId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TopicType topicType;


    public enum TopicType {
        ARTIST,
        ALBUM,
        SONG
    }


    /**
     * Constructors
     */

    public Post() {
    }

    public Post(UUID userId, Instant createdAt, UUID topicId, String content) {

        this.userId = userId;
        this.createdAt = createdAt;
        this.topicId = topicId;
        this.content = content;
    }
}
