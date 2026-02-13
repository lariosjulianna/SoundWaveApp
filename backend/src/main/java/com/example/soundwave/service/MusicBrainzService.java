package com.example.soundwave.service;
import java.util.UUID;

import com.example.soundwave.dto.musicbrainz.ArtistDto;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
import java.util.List;

@Service
public class MusicBrainzService {

    // dummy data first!!!

        public List<ArtistDto> getRandomArtists() {
            return List.of(
                    new ArtistDto(UUID.randomUUID(), "The Beatles"),
                    new ArtistDto(UUID.randomUUID(), "Beyoncé"),
                    new ArtistDto(UUID.randomUUID(), "Radiohead")
            );
        }

        public List<String> getAlbumsByArtist(String id) {
            return List.of(
                    "Greatest Hits",
                    "Live at Somewhere",
                    "Debut Album"
            );
        }

    public List<String> getSongsByAlbum(String albumId) {
        return List.of(
                "Track One",
                "Track Two",
                "Track Three"
        );
    }

}

//    private static final String BASE_URL = "https://musicbrainz.org/ws/2";
//    private final RestTemplate restTemplate = new RestTemplate();
//    private final ObjectMapper mapper = new ObjectMapper();
//
//    public List<String> searchArtists(String query) {
//        String url = BASE_URL + "/artist?query=" + query + "&limit=3&fmt=json";
//
//        String response = restTemplate.getForObject(url, String.class);
//
//        List<String> artists = new ArrayList<>();
//
//        try {
//            JsonNode root = mapper.readTree(response);
//            for (JsonNode artist : root.get("artists")) {
//                artists.add(artist.get("name").asText());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return artists;
//    }
//
//
//    /**
//     * Returns three random artists for testing.
//     */
//    public List<ArtistDto> getRandomArtists() {
//        List<ArtistDto> artists = new ArrayList<>();
//
//        artists.add(new ArtistDto(UUID.randomUUID(), "The Beatles"));
//        artists.add(new ArtistDto(UUID.randomUUID(), "Beyoncé"));
//        artists.add(new ArtistDto(UUID.randomUUID(), "Coldplay"));
//
//        return artists;
//    }
//
//    /**
//     * Returns dummy album names for a given artist ID.
//     */
//    public List<String> getAlbumsByArtist(String artistId) {
//        List<String> albums = new ArrayList<>();
//        albums.add("Greatest Hits Vol. 1");
//        albums.add("The Live Sessions");
//        albums.add("Debut Album");
//
//        return albums;
//    }
//}
//

