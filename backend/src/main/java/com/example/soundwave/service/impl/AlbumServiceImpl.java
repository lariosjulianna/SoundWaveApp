package com.example.soundwave.service.impl;

import com.example.soundwave.dto.musicbrainz.AlbumDto;
import com.example.soundwave.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of AlbumService
 *
 * Returns dummy data for dev
 * Later backed external API (MusicBrainz)
 *
 * Delegates external API calls to MusicBrainzService
 */

@Service
public class AlbumServiceImpl implements AlbumService {

    /**
     * Temporary in-memory album list (dummy DB)
     */

    private static final List<AlbumDto> ALBUMS = List.of(
            new AlbumDto(UUID.randomUUID(), "Heaven Knows", UUID.randomUUID()),
            new AlbumDto(UUID.randomUUID(), "Views", UUID.randomUUID()),
            new AlbumDto(UUID.randomUUID(), "Sling", UUID.randomUUID())
    );


    /**
     * Returns a random/sample list of albums
     */

    @Override
    public List<AlbumDto> getRandomAlbums() {
        return ALBUMS;
    }


    /**
     * Searches albums by title (case-insensitive)
     */

    @Override
    public List<AlbumDto> searchAlbums(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }

        String normalized = query.toLowerCase();

        return ALBUMS.stream()
                .filter(album -> album.getTitle().toLowerCase().contains(normalized))
                .collect(Collectors.toList());
    }


    /**
     * Fetch a single album by ID
     */

    @Override
    public AlbumDto getAlbumById(String albumId) {
        return ALBUMS.stream()
                .filter(album -> album.getId().toString().equals(albumId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Album not found: " + albumId)
                );
    }
}
