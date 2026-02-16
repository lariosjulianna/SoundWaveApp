//package backend.src.main.java.com.example.soundwave.repository;
package com.example.soundwave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
import com.example.soundwave.entity.Artist;


// public class ArtistRepository {
    
// }
public interface ArtistRepository extends JpaRepository<Artist, UUID> {
    List<Artist> findByName(String name);
}
