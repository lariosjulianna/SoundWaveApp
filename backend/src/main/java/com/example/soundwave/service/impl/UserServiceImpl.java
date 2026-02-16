package com.example.soundwave.service.impl;

import com.example.soundwave.dto.musicbrainz.UserDto;
import com.example.soundwave.service.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of UserService
 *
 * Returns dummy data for dev
 * Later db access
 */

@Service
public class UserServiceImpl implements UserService {

    /**
     * Temporary in-mem user list
     */

    private static final List<UserDto> USERS = List.of(
            new UserDto(
                    UUID.randomUUID(),
                    "joe",
                    "joemama",
                    "joe@gmail.com"

            ),
            new UserDto(
                    UUID.randomUUID(),
                    "lee",
                    "leemama",
                    "lee@gmail.com"

            )
    );


    /**
     * Returns random users
     */

    @Override
    public List<UserDto> getRandomUsers() {
        return USERS;
    }


    /**
     * Search users by username
     */

    @Override
    public List<UserDto> searchUsers(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }

        String normalized = query.toLowerCase();

        return USERS.stream()
                .filter(user -> user.getName().toLowerCase().contains(normalized))
                .collect(Collectors.toList());
    }


    /**
     * Get a single user by Id
     */

    @Override
    public UserDto getUserById(String userId) {
        return USERS.stream()
                .filter(user -> user.getId().toString().equals(userId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found: " + userId)
                );
    }
}