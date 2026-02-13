package com.example.soundwave.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    public Song() {}

    public Song(String title, Album album, Artist artist) {
        this.title = title;
        this.album = album;
        this.artist = artist;
    }

    // Getters and setters
    // use lombok later

    public UUID getId() {

        return id;
    }

    public String getTitle() {

        return title;
    }

    public Album getAlbum() {

        return album;
    }

    public void setId(UUID id) {

        this.id = id;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setAlbum(Album album) {

        this.album = album;
    }
}
