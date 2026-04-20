package com.example.soundwave.service.impl;

import com.example.soundwave.dto.musicbrainz.AlbumDto;
import com.example.soundwave.dto.musicbrainz.SongDto;
import com.example.soundwave.service.AlbumService;
import com.example.soundwave.service.MusicBrainzService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final MusicBrainzService musicBrainzService;

    public AlbumServiceImpl(MusicBrainzService musicBrainzService) {
        this.musicBrainzService = musicBrainzService;
    }

    @Override
    public List<AlbumDto> getRandomAlbums() {
        return musicBrainzService.sampleAlbums();
    }

    @Override
    public List<AlbumDto> searchAlbums(String query) {
        return musicBrainzService.searchAlbums(query);
    }

    @Override
    public AlbumDto getAlbumById(String albumId) {
        return musicBrainzService.getAlbumByMbid(albumId);
    }

    @Override
    public List<SongDto> getSongsForAlbum(String albumId) {
        return musicBrainzService.getRecordingsForReleaseGroup(albumId);
    }
}
