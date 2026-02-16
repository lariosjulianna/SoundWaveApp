package com.example.soundwave.service.impl;

import com.example.soundwave.dto.musicbrainz.ArtistDto;
import com.example.soundwave.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of ArtistService
 *
 * Returns dummy data for dev
 * Later backed external API (MusicBrainz)
 *
 * Delegates external API calls to MusicBrainzService
 */

@Service
public class ArtistServiceImpl implements ArtistService {

    /**
     * Temporary in-memory artist list (dummy DB)
     */

    private static final List<ArtistDto> ARTISTS = List.of(
            new ArtistDto(UUID.randomUUID(), "PinkPantheress"),
            new ArtistDto(UUID.randomUUID(), "Junior H"),
            new ArtistDto(UUID.randomUUID(), "Clairo")
    );


    /**
     * Returns a random/sample list of artists
     */

    @Override
    public List<ArtistDto> getRandomArtists() {

        return ARTISTS;
    }


    /**
     * Searches artists by name (case-insensitive)
     */

    @Override
    public List<ArtistDto> searchArtists(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }

        String normalized = query.toLowerCase();

        return ARTISTS.stream()
                .filter(a -> a.getName().toLowerCase().contains(normalized))
                .collect(Collectors.toList());
    }


    /**
     * Fetch a single artist by ID
     */

    @Override
    public ArtistDto getArtistById(String artistId) {
        return ARTISTS.stream()
                .filter(artist -> artist.getId().toString().equals(artistId))
                .findFirst()
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Artist not found: " + artistId
                        )
                );
    }
}
