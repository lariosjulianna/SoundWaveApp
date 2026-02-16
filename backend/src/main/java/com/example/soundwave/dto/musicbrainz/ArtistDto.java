package com.example.soundwave.dto.musicbrainz;

import java.util.UUID;

public class ArtistDto {

    private final UUID id;
    private final String name;


    /**
     * Constructor
     */

    public ArtistDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }


    /**
     * Getters
     */

    public UUID getId() {

        return id;
    }

    public String getName() {

        return name;
    }
}
//    /**
//     * Setters (always void)
//     */
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

