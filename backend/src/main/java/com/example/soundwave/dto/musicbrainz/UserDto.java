package com.example.soundwave.dto.musicbrainz;

import java.util.UUID;

public class UserDto {

    private UUID id;
    private String name;
    private String username;
    private String email;

    public UserDto(UUID id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
}
