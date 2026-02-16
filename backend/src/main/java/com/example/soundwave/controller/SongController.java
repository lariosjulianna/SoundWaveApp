package com.example.soundwave.controller;

import com.example.soundwave.dto.musicbrainz.SongDto;
import com.example.soundwave.service.SongService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller responsible for song-related API endpoints
 *
 * Handles fetching songs via the MusicBrainz service
 */

@RestController
@RequestMapping("/songs")
@CrossOrigin(origins = "http://localhost:5173")
public class SongController {

    private final SongService songService;

    /**
     * Constructor-based dependency injection
     *
     * @param songService used to interact with the MusicBrainz API
     */

    public SongController(SongService songService) {

        this.songService = songService;
    }


    /**
     * Return a list of random songs
     */

    @GetMapping("/random")
    public List<SongDto> getRandomSongs() {

        return songService.getRandomSongs();
    }


    /**
     * Search for songs by name
     */

    @GetMapping("/search")
    public List<SongDto> searchSongs(@RequestParam String q) {

        return songService.searchSongs(q);
    }


    /**
     * Get a single song by Id
     */

    @GetMapping("/{SongId}")
    public SongDto getSong(@PathVariable String SongId) {

        return songService.getSongById(SongId);
    }
}
