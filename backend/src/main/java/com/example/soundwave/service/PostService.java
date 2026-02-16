package com.example.soundwave.service;

import com.example.soundwave.dto.musicbrainz.PostDto;
import java.util.List;

/**
 * Service interface for post-related business logic
 *
 * This layer abstracts where post data comes from
 * (database, cache, etc.)
 */

public interface PostService {

    /**
     * Returns a list of random posts
     *
     * @return list of PostDto
     */

    List<PostDto> getRandomPosts();


    /**
     * Search posts by content
     *
     * @param query search keyword
     * @return list of matching posts
     */

    List<PostDto> searchPosts(String query);


    /**
     * Fetch a single post by ID
     *
     * @param postId post ID
     * @return PostDto
     */

    PostDto getPostById(String postId);


    /**
     * Fetch posts for a specific topic (artist, album, song)
     */

    //List<PostDto> getPostsByTopic(UUID topicId, TopicType topicType);
}
