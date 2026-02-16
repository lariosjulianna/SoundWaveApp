package com.example.soundwave.controller;

import com.example.soundwave.dto.musicbrainz.UserDto;
import com.example.soundwave.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller responsible for user-related API endpoints
 *
 */

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    /**
     * Constructor-based dependency injection
     *
     * @param userService used to interact
     */

    public UserController(UserService userService) {

        this.userService = userService;
    }

    /**
     * Return a list of random users
     */

    @GetMapping("/random")
    public List<UserDto> getRandomUsers() {

        return userService.getRandomUsers();
    }


    /**
     * Search for userxs by name
     */

    @GetMapping("/search")
    public List<UserDto> searchUsers(@RequestParam String q) {

        return userService.searchUsers(q);
    }


    /**
     * Get a single user by Id
     */

    @GetMapping("/{UserId}")
    public UserDto getUser(@PathVariable String UserId) {

        return userService.getUserById(UserId);
    }
}
