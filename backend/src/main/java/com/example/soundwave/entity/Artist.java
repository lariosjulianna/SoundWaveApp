package com.example.soundwave.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    // private String country;

    // * Can add genre ? dob?

    // Constructors
    public Artist() {}

    public Artist(String name) {

        this.name = name;
    }

    // Getters and setters
    public UUID getId() {

        return id;
    }

    public void setId(UUID id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}
