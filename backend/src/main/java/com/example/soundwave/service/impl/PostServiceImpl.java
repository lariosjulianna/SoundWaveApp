package com.example.soundwave.service.impl;

import com.example.soundwave.dto.musicbrainz.PostDto;
import com.example.soundwave.entity.Post;
import com.example.soundwave.exception.ResourceNotFoundException;
import com.example.soundwave.repository.PostRepository;
import com.example.soundwave.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> getRandomPosts() {
        List<Post> posts = postRepository.findTop50ByOrderByCreatedAtDesc();
        if (posts.isEmpty()) {
            return List.of();
        }
        List<Post> shuffled = posts.stream().collect(Collectors.toList());
        Collections.shuffle(shuffled);
        int limit = Math.min(20, shuffled.size());
        return shuffled.subList(0, limit).stream()
                .map(PostServiceImpl::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }
        return postRepository.findByContentContainingIgnoreCase(query.trim()).stream()
                .map(PostServiceImpl::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(String postId) {
        UUID id = parsePostId(postId);
        Post post = postRepository.findFetchedById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found: " + postId));
        return toDto(post);
    }

    private static UUID parsePostId(String postId) {
        try {
            return UUID.fromString(postId);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Post not found: " + postId);
        }
    }

    private static PostDto toDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getUser().getUsername(),
                post.getTopicId(),
                post.getTopicType().name(),
                post.getTopicName(),
                post.getContent(),
                post.getCreatedAt()
        );
    }
}
