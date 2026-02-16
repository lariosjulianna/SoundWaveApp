package com.example.soundwave.dto.musicbrainz;

import java.util.UUID;

public class UserDto {

    private final UUID id;
    private final String name;
    private final String username;
    private final String email;


    /**
     * Constructor
     */

    public UserDto(UUID id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }


    /**
     * Getters
     */

    public UUID getId() { return id; }

    public String getName() { return name; }

    public String getUsername() { return username; }

    public String getEmail() { return email; }
}
