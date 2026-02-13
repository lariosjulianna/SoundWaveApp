package com.example.soundwave.service;

import com.example.soundwave.dto.musicbrainz.PostDto;
import com.example.soundwave.entity.Post.TopicType;

import java.util.List;
import java.util.UUID;

public interface PostService {

    /**
     * Returns a list of random posts
     */
    List<PostDto> getRandomPosts();

    /**
     * Search posts by content
     */
    List<PostDto> searchPosts(String query);

    /**
     * Fetch a single post by ID
     */
    PostDto getPostById(UUID postId);

    /**
     * Fetch posts for a specific topic (artist, album, song)
     */
    List<PostDto> getPostsByTopic(UUID topicId, TopicType topicType);
}
