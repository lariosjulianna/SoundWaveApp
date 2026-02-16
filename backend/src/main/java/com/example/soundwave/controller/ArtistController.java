package com.example.soundwave.controller;

import com.example.soundwave.dto.musicbrainz.ArtistDto;
import com.example.soundwave.service.ArtistService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller responsible for artist-related API endpoints
 *
 * Handles fetching artists via the MusicBrainz service
 */

@RestController
@RequestMapping("/artists")
@CrossOrigin(origins = "http://localhost:5173") // allows frontend dev server access
public class ArtistController {

    private final ArtistService artistService;

    /**
     * Constructor-based dependency injection
     *
     * @param artistService used to interact with the MusicBrainz API
     */

    public ArtistController(ArtistService artistService) {

        this.artistService = artistService;
    }


    /**
     * Return a list of random artists
     */

    @GetMapping("/random")
    public List<ArtistDto> getRandomArtists() {
        return artistService.getRandomArtists();
    }


    /**
     * Search for artists by name
     */

    @GetMapping("/search")
    public List<ArtistDto> searchArtists(@RequestParam String q) {
        return artistService.searchArtists(q);
    }


    /**
     * Get a single artist by ID
     */

    @GetMapping("/{artistId}")
    public ArtistDto getArtist(@PathVariable String artistId) {
        return artistService.getArtistById(artistId);
    }

}




