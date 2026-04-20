package com.example.soundwave.service.impl;

import com.example.soundwave.dto.musicbrainz.AlbumDto;
import com.example.soundwave.dto.musicbrainz.ArtistDto;
import com.example.soundwave.service.ArtistService;
import com.example.soundwave.service.MusicBrainzService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final MusicBrainzService musicBrainzService;

    public ArtistServiceImpl(MusicBrainzService musicBrainzService) {
        this.musicBrainzService = musicBrainzService;
    }

    @Override
    public List<ArtistDto> getRandomArtists() {
        return musicBrainzService.sampleArtists();
    }

    @Override
    public List<ArtistDto> searchArtists(String query) {
        return musicBrainzService.searchArtists(query);
    }

    @Override
    public ArtistDto getArtistById(String artistId) {
        return musicBrainzService.getArtistByMbid(artistId);
    }

    @Override
    public List<AlbumDto> getAlbumsForArtist(String artistId) {
        return musicBrainzService.getReleaseGroupsForArtist(artistId);
    }
}
