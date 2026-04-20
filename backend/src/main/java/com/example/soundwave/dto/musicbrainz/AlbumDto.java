package com.example.soundwave.dto.musicbrainz;

import java.util.UUID;

public class AlbumDto {

    private final UUID id;
    private final String title;
    //private final UUID artistId;
    private final String artistName;


    /**
     * Constructor
     */

    public AlbumDto(UUID id, String title, String artistName) {
        this.id = id;
        this.title = title;
        //this.artistId = artistId;
        this.artistName = artistName;
    }


    /**
     * Getters
     */

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

//    public UUID getArtistId() {
//        return artistId;
//    }

    public String getArtistName() {
        return artistName;
    }
}
//    // setters
//
//    public void setId(UUID id) {
//        this.id = id;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setArtistId(UUID artistId) {
//        this.artistId = artistId;
//    }
//
//
//}
