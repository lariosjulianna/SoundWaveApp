package com.example.soundwave.controller;

import com.example.soundwave.dto.musicbrainz.PostDto;
import com.example.soundwave.service.PostService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "http://localhost:5173")
public class PostController {

    private final PostService postService;

    /**
     * Constructor-based dependency injection
     *
     * @param postService used to interact with the MusicBrainz API
     */

    public PostController(PostService postService) {

        this.postService = postService;
    }


    /**
     * Return a list of random posts
     */

    @GetMapping("/random")
    public List<PostDto> getRandomPosts() {

        return postService.getRandomPosts();
    }


    /**
     * Search for posts by content
     */

    @GetMapping("/search")
    public List<PostDto> searchPosts(@RequestParam String q) {

        return postService.searchPosts(q);
    }


    /**
     * Get a single post by Id
     */

    @GetMapping("/{PostId}")
    public PostDto getPost(@PathVariable String PostId) {

        return postService.getPostById(PostId);
    }
}
