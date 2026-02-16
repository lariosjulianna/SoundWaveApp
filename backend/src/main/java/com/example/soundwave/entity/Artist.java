package com.example.soundwave.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "artists")
@Getter
@Setter
public class Artist {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    // ! private String country;


    /**
     * Constructors
     */

    public Artist() {
    }

    public Artist(String name) {

        this.name = name;
    }

}