package com.example.soundwave.dto.musicbrainz;

import java.util.UUID;

//@Getter
public class AlbumDto {

    private UUID id;
    private String title;
    private UUID artistId;

    public AlbumDto(UUID id, String title, UUID artistId) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public UUID getArtistId() {
        return artistId;
    }

    // setters

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtistId(UUID artistId) {
        this.artistId = artistId;
    }


}
