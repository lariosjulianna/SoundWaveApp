package com.example.soundwave.dto.musicbrainz;

import java.util.UUID;

public class ArtistDto {

    private UUID id;
    private String name;
    //dob
    // email
    // hashed pass?

    public ArtistDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    // setter should be void
    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
