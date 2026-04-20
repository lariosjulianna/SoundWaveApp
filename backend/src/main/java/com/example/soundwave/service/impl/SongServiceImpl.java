package com.example.soundwave.service.impl;

import com.example.soundwave.dto.musicbrainz.SongDto;
import com.example.soundwave.service.MusicBrainzService;
import com.example.soundwave.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private final MusicBrainzService musicBrainzService;

    public SongServiceImpl(MusicBrainzService musicBrainzService) {
        this.musicBrainzService = musicBrainzService;
    }

    @Override
    public List<SongDto> getRandomSongs() {
        return musicBrainzService.sampleSongs();
    }

    @Override
    public List<SongDto> searchSongs(String query) {
        return musicBrainzService.searchSongs(query);
    }

    @Override
    public SongDto getSongById(String songId) {
        return musicBrainzService.getSongByMbid(songId);
    }
}
