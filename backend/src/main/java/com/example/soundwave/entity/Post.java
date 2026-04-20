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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The user who created the post.
     * Many posts can belong to one user
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    /**
     * The ID of the entity this post references (Artist, Album, or Song).
     */

    @Column(nullable = false)
    private UUID topicId;

    /**
     * Indicates what type of entity topicId refers to.
     */

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TopicType topicType;

    /**
     * Enum representing the type of content referenced.
     */
    public enum TopicType {
        ARTIST,
        ALBUM,
        SONG
    }

    /**
     * topic Name
     */

    @Column(nullable = false, length = 1000)
    private String topicName;


    /**
     * Main body content of the post.
     */

    @Column(nullable = false, length = 1000)
    private String content;



    /**
     * The time the post was created.
     */
    @Column(nullable = false, updatable = false)
    private Instant createdAt;


    /**
     * Automatically set createdAt before persisting.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
    }

    public Post() {
    }

    public Post(User user, UUID topicId, TopicType topicType, String topicName, String content) {
        this.user = user;
        this.topicId = topicId;
        this.topicType = topicType;
        this.topicName = topicName;
        this.content = content;
    }
}


//package com.example.soundwave.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.Instant;
//import java.util.UUID;
//
//@Entity
//@Table(name = "posts")
//@Getter
//@Setter
//public class Post {
//
//    @Id
//    @GeneratedValue
//    private UUID id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "userId", nullable = false)
//    private User user;
//
//
//    @Column(nullable = false)
//    private Instant createdAt;
//
//    @Column(nullable = false)
//    private UUID topicId;
//
//    @Column(nullable = false, length = 1000)
//    private String content;
//
//    // ! finish
//    /**
//     * Enumerated types allow...
//     */
//
////    private UUID topicId;
//
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private TopicType topicType;
//
//
//    public enum TopicType {
//        ARTIST,
//        ALBUM,
//        SONG
//    }
//
//
//    /**
//     * Constructors
//     */
//
//    public Post() {
//    }
//
//    public Post(UUID userId, Instant createdAt, UUID topicId, String content) {
//
//        //this.user = user;
//        this.createdAt = createdAt;
//        this.topicId = topicId;
//        this.content = content;
//    }
//}
