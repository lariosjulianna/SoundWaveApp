// ! uncomment
//package com.example.soundwave.service.impl;
//
//import com.example.soundwave.dto.musicbrainz.PostDto;
////import com.example.soundwave.dto.musicbrainz.SongDto;
//import com.example.soundwave.entity.Post;
//import com.example.soundwave.repository.PostRepository;
//import com.example.soundwave.service.PostService;
//
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Random;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@Service
//public class PostServiceImpl implements PostService {
//
////    private final PostRepository postRepository;
////
////    public PostServiceImpl(PostRepository postRepository) {
////        this.postRepository = postRepository;
////    }
//
//    /**
//     * Temporary in-mem post list
//     */
//    private static final List<PostDto> POSTS = List.of(
//            new PostDto(UUID.randomUUID(), UUID.randomUUID(), ddd, ff),
////            new SongDto(UUID.randomUUID(), "Vueltas", UUID.randomUUID(), UUID.randomUUID()),
////            new SongDto(UUID.randomUUID(), "Ay Vamos", UUID.randomUUID(), UUID.randomUUID())
//    );
//
//
//    /**
//     * Returns random posts
//     */
//    @Override
//    public List<PostDto> getRandomPosts() {
//        return POSTS;
////        List<Post> posts = postRepository.findAll();
////
////        // Simple shuffle logic
////        posts.sort((a, b) -> new Random().nextInt(3) - 1);
////
////        return posts.stream()
////                .limit(10)
////                .map(this::mapToDto)
////                .collect(Collectors.toList());
//    }
//
//    /**
//     * Search posts by content
//     */
//    @Override
//    public List<PostDto> searchPosts(String query) {
//        if (query == null || query.isBlank()) {
//            return List.of();
//        }
//
//        String normalized = query.toLowerCase();
//
//        return POSTS.stream()
//                .filter(post -> post.getContent().toLowerCase().contains(normalized))
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Get a single post by ID
//     */
//    @Override
//    public PostDto getPostById(String postId) {
//        return POSTS.stream()
//                .filter(post -> post.getId().toString().equals(postId))
//                .findFirst()
//                .orElseThrow(() ->
//                        new IllegalArgumentException("Post not found: " + postId)
//                );
//    }
//
//}
//
//
//


//package com.example.soundwave.service.impl;
//
//import com.example.soundwave.dto.musicbrainz.PostDto;
//import com.example.soundwave.entity.Post.TopicType;
//import com.example.soundwave.service.PostService;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.time.Instant;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@Service
//public class PostServiceImpl implements PostService {
//
//    private static final List<PostDto> POSTS = List.of(
//            new PostDto(
//                    UUID.randomUUID(),
//                    UUID.randomUUID(),
//                    //UUID.randomUUID(),
//                    Instant.now(),
//                    TopicType.ARTIST,
//                    "This artist is amazing live."
//
//            ),
//            new PostDto(
//                    UUID.randomUUID(),
//                    UUID.randomUUID(),
//                    UUID.randomUUID(),
//                    TopicType.ALBUM,
//                    "This album has no skips.",
//                    Instant.now()
//            )
//    );
//
//    @Override
//    public List<PostDto> getRandomPosts() {
//        return POSTS;
//    }
//
//    @Override
//    public List<PostDto> getPostsByTopic(UUID topicId, TopicType topicType) {
//        return POSTS.stream()
//                .filter(post ->
//                        post.getTopicId().equals(topicId)
//                                && post.getTopicId() == topicType
//                )
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public PostDto getPostById(UUID postId) {
//        return POSTS.stream()
//                .filter(post -> post.getId().equals(postId))
//                .findFirst()
//                .orElseThrow(() ->
//                        new ResponseStatusException(
//                                HttpStatus.NOT_FOUND,
//                                "Post not found: " + postId
//                        )
//                );
//    }
//}
