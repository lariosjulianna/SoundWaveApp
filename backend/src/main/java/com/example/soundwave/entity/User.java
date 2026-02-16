package com.example.soundwave.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;


@Entity
@Table(name = "users")
@Getter
@Setter

/**
 * Using Lombok for Getters/Setters
 */

public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwordHash;


    /**
     * Constructors
     */

    public User() {}

    public User(String name, String username, String email, String passwordHash) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}


