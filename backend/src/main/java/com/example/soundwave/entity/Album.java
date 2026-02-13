package com.example.soundwave.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    private Artist artist;

    public Album() {}

    public Album(String title, Artist artist) {

        this.title = title;
        this.artist = artist;
    }

    // Getters and setters
    public UUID getId() {

        return id;
    }

    public String getTitle() {

        return title;
    }

    public Artist getArtist() {

        return artist;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
