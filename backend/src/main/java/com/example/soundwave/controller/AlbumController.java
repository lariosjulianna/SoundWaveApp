package com.example.soundwave.controller;

import com.example.soundwave.dto.musicbrainz.AlbumDto;
import com.example.soundwave.service.AlbumService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST controller responsible for album-related API endpoints
 *
 * Handles fetching aalbums via the MusicBrainz service
 */

@RestController
@RequestMapping("/albums")
@CrossOrigin(origins = "http://localhost:5173") // allows frontend dev server access
public class AlbumController {

    private final AlbumService albumService;

    /**
     * Constructor-based dependency injection
     *
     * @param albumService used to interact with the MusicBrainz API
     */

    public AlbumController(AlbumService albumService) {

        this.albumService = albumService;
    }


    /**
     * Return a list of random albums
     */

    @GetMapping("/random")
    public List<AlbumDto> getRandomAlbums() {
        return albumService.getRandomAlbums();
    }


    /**
     * Search for albums by name
     */

    @GetMapping("/search")
    public List<AlbumDto> searchAlbums(@RequestParam String q) {

        return albumService.searchAlbums(q);
    }


    /**
     * Get a single album by Id
     */

    @GetMapping("/{AlbumId}")
    public AlbumDto getAlbum(@PathVariable String AlbumId) {

        return albumService.getAlbumById(AlbumId);
    }

}




