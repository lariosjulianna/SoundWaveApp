package com.example.soundwave.dto.musicbrainz;

import java.util.UUID;

public class SongDto {

    private UUID id;
    private String title;
    private UUID albumId;
    private UUID artistId;

    public SongDto(UUID id, String title, UUID albumId, UUID artistId) {
        this.id = id;
        this.title = title;
        this.albumId = albumId;
        this.artistId = artistId;
    }

    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public UUID getAlbumId() { return albumId; }
    public UUID getArtistId() { return artistId; }
}
