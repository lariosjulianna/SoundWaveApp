package com.example.soundwave.service.impl;

import com.example.soundwave.dto.musicbrainz.SongDto;
import com.example.soundwave.service.SongService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of SongService
 *
 * Returns dummy data for dev
 *
 * Delegates external API calls to MusicBrainzService
 */

@Service
public class SongServiceImpl implements SongService {

    /**
     * Temporary in-mem song list
     */

    private static final List<SongDto> SONGS = List.of(
            new SongDto(UUID.randomUUID(), "Girl Like Me", UUID.randomUUID(), UUID.randomUUID()),
            new SongDto(UUID.randomUUID(), "Vueltas", UUID.randomUUID(), UUID.randomUUID()),
            new SongDto(UUID.randomUUID(), "Ay Vamos", UUID.randomUUID(), UUID.randomUUID())
    );


    /**
     * Returns a random/sample list of songs
     */

    @Override
    public List<SongDto> getRandomSongs() {
        return SONGS;
    }


    /**
     * Searches songs by title (case-insensitive)
     */

    @Override
    public List<SongDto> searchSongs(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }

        String normalized = query.toLowerCase();

        return SONGS.stream()
                .filter(song -> song.getTitle().toLowerCase().contains(normalized))
                .collect(Collectors.toList());
    }


    /**
     * Fetch a single song by Id
     */

    @Override
    public SongDto getSongById(String songId) {
        return SONGS.stream()
                .filter(song -> song.getId().toString().equals(songId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Song not found: " + songId)
                );
    }
}


