package com.example.soundwave.service;

import com.example.soundwave.dto.musicbrainz.AlbumDto;
import java.util.List;

/**
 * Service interface for album-related business logic
 *
 * This layer abstracts where artist data comes from
 * (MusicBrainz, database, cache, etc.)
 */

public interface AlbumService {

    /**
     * Returns a list of random albums
     *
     * @return list of AlbumDto
     */

    List<AlbumDto> getRandomAlbums();


    /**
     * Search for albums by name
     *
     * @param query search keyword
     * @return list of matching albums
     */

    List<AlbumDto> searchAlbums(String query);


    /**
     * Fetch a single album by ID
     *
     * @param albumId MusicBrainz album ID
     * @return AlbumDto
     */

    AlbumDto getAlbumById(String albumId);
}