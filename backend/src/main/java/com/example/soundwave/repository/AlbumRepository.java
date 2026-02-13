package com.example.soundwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;
import com.example.soundwave.entity.Album;


// }
public interface AlbumRepository extends JpaRepository<Album, UUID> {
    Optional<Album> findByTitle(String title);
}
