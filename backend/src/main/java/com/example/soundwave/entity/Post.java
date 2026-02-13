package com.example.soundwave.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "posts")
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
    // define the enum

    /**
     * Enumerated types allow
     */

//    private UUID topicId;
//
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TopicType topicType;



    public enum TopicType {
        ARTIST,
        ALBUM,
        SONG
    }

    // Constructor

    public Post() {}

    public Post(UUID userId, Instant createdAt, UUID topicId, String content) {

        this.userId = userId;
        this.createdAt = createdAt;
        this.topicId = topicId;
        this.content = content;
    }

    // getters/setters

    public UUID getId() {

        return id;
    }

    public void setId(UUID id) {

        this.id = id;
    }

    public UUID getUserId() {

        return userId;
    }

    public void setUserId(UUID userId) {

        this.userId = userId;
    }

    public Instant getCreatedAt() {

        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {

        this.createdAt = createdAt;
    }

    public UUID getTopicId() {

        return topicId;
    }

    public void setTopicId(UUID topicId) {

        this.topicId = topicId;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }


}
