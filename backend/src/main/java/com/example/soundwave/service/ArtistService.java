package com.example.soundwave.service;

import com.example.soundwave.dto.musicbrainz.ArtistDto;
import java.util.List;

/**
 * Service interface for artist-related business logic
 *
 * This layer abstracts where artist data comes from
 * (MusicBrainz, database, cache, etc.)
 */

public interface ArtistService {

    /**
     * Returns a list of random artists.
     *
     * @return list of ArtistDto
     */

    List<ArtistDto> getRandomArtists();


    /**
     * Search for artists by name.
     *
     * @param query search keyword
     * @return list of matching artists
     */

    List<ArtistDto> searchArtists(String query);


    /**
     * Fetch a single artist by ID.
     *
     * @param artistId MusicBrainz artist ID
     * @return ArtistDto
     */

    ArtistDto getArtistById(String artistId);
}