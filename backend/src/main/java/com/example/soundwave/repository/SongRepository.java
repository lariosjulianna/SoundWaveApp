package com.example.soundwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;
import com.example.soundwave.entity.Song;


public interface SongRepository extends JpaRepository<Song, UUID> {
    Optional<Song> findByTitle(String title);
}
