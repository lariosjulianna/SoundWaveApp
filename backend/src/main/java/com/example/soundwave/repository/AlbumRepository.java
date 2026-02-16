package com.example.soundwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
import com.example.soundwave.entity.Album;


// }
public interface AlbumRepository extends JpaRepository<Album, UUID> {
    List<Album> findByTitle(String title);
}
