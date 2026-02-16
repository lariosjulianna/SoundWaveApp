package com.example.soundwave.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "albums")
@Getter
@Setter

public class Album {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    private Artist artist;


    /**
     * Constructors
     */

    public Album() {
    }

    public Album(String title, Artist artist) {

        this.title = title;
        this.artist = artist;
    }
}