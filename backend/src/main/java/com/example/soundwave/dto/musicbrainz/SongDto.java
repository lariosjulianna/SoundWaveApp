package com.example.soundwave.dto.musicbrainz;

import java.util.UUID;

public class SongDto {

    private final UUID id;
    private final String title;
    private final UUID albumId;
    private final UUID artistId;


    /**
     * Constructor
     */

    public SongDto(UUID id, String title, UUID albumId, UUID artistId) {
        this.id = id;
        this.title = title;
        this.albumId = albumId;
        this.artistId = artistId;
    }


    /**
     * Getters
     */

    public UUID getId() { return id; }

    public String getTitle() { return title; }

    public UUID getAlbumId() { return albumId; }

    public UUID getArtistId() { return artistId; }
}
