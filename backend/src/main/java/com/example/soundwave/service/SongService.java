package com.example.soundwave.service;

import com.example.soundwave.dto.musicbrainz.SongDto;
import java.util.List;

/**
 * Service interface for artist-related business logic
 *
 * This layer abstracts where artist data comes from
 * (MusicBrainz, db, cache...)
 */

public interface SongService {

    /**
     * Returns a list of random songs
     *
     * @return list of SongDto
     */

    List<SongDto> getRandomSongs();


    /**
     * Search for songs by name
     *
     * @param query search keyword
     * @return list of matching songs
     */

    List<SongDto> searchSongs(String query);


    /**
     * Fetch a single song by Id
     *
     * @param songId MusicBrainz song Id
     * @return SongDto
     */

    SongDto getSongById(String songId);


}
