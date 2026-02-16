package com.example.soundwave.service.impl;

import com.example.soundwave.dto.musicbrainz.PostDto;
import com.example.soundwave.service.PostService;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of PostService
 *
 * Returns dummy data for dev
 * Later backed external API (MusicBrainz)
 *
 * Delegates external API calls to MusicBrainzService
 */

@Service
public class PostServiceImpl implements PostService {

    /**
     * Temporary in-mem post list
     */

    private static final List<PostDto> POSTS = List.of(
            new PostDto(
                    UUID.fromString("11111111-1111-1111-1111-111111111111"),
                    UUID.fromString("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"),
                    Instant.now(),
                    UUID.fromString("bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb"),
                    "This song is insane 🔥"
            ),
            new PostDto(
                    UUID.fromString("22222222-2222-2222-2222-222222222222"),
                    UUID.fromString("cccccccc-cccc-cccc-cccc-cccccccccccc"),
                    Instant.now(),
                    UUID.fromString("dddddddd-dddd-dddd-dddd-dddddddddddd"),
                    "Album of the year."
            )
    );


    /**
     * Returns random posts
     */

    @Override
    public List<PostDto> getRandomPosts() {
        return POSTS;
    }


    /**
     * Search posts by content
     */

    @Override
    public List<PostDto> searchPosts(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }

        String normalized = query.toLowerCase();

        return POSTS.stream()
                .filter(post -> post.getContent().toLowerCase().contains(normalized))
                .collect(Collectors.toList());
    }


    /**
     * Get a single post by ID
     */

    @Override
    public PostDto getPostById(String postId) {
        return POSTS.stream()
                .filter(post -> post.getId().toString().equals(postId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Post not found: " + postId)
                );
    }

//    @Override
//    public List<PostDto> getPostsByTopic(UUID topicId, Post.TopicType topicType) {
//        return POSTS.stream()
//                .filter(post ->
//                        post.getTopicId().equals(topicId) &&
//                                post.getTopicType() == topicType
//                )
//                .collect(Collectors.toList());
//    }

}

