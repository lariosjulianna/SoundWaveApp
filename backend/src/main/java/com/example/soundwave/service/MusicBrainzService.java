package com.example.soundwave.service;

import com.example.soundwave.dto.musicbrainz.AlbumDto;
import com.example.soundwave.dto.musicbrainz.ArtistDto;
import com.example.soundwave.dto.musicbrainz.SongDto;
import com.example.soundwave.exception.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class MusicBrainzService {

    private static final String BASE = "https://musicbrainz.org/ws/2";
    private static final UUID UNKNOWN_ID = new UUID(0L, 0L);

    private final RestTemplate restTemplate;
    private final MusicBrainzRateLimiter rateLimiter;

    /**
     * Dedicated mapper for MusicBrainz JSON. Spring Boot 4 does not always register an {@link ObjectMapper} bean,
     * so we avoid constructor injection here.
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MusicBrainzService(RestTemplate restTemplate, MusicBrainzRateLimiter rateLimiter) {
        this.restTemplate = restTemplate;
        this.rateLimiter = rateLimiter;
    }

    /**
     * Fetch JSON as text then parse — more reliable than {@code getForObject(url, JsonNode.class)}
     * with some RestTemplate / converter setups.
     */
    private JsonNode fetchJson(String url) {
        try {
            String body = restTemplate.getForObject(url, String.class);
            if (body == null || body.isBlank()) {
                return null;
            }
            return objectMapper.readTree(body);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("MusicBrainz returned invalid JSON", e);
        } catch (HttpClientErrorException e) {
            if (isNotFound(e.getStatusCode())) {
                return null;
            }
            throw new IllegalStateException("MusicBrainz HTTP error: " + e.getStatusCode(), e);
        } catch (RestClientException e) {
            throw new IllegalStateException("MusicBrainz request failed: " + e.getMessage(), e);
        }
    }

    private static boolean isNotFound(HttpStatusCode code) {
        return code.value() == 404;
    }

    /**
     * A few real artists (not random — MusicBrainz has no random endpoint).
     */
    public List<ArtistDto> sampleArtists() {
        return searchArtists("a");
    }

    public List<ArtistDto> searchArtists(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }
        rateLimiter.acquire();
        String url = UriComponentsBuilder.fromUriString(BASE + "/artist")
                .queryParam("query", query)
                .queryParam("fmt", "json")
                .queryParam("limit", 25)
                .encode(StandardCharsets.UTF_8)
                .toUriString();
        JsonNode root = fetchJson(url);
        return mapArtistSearch(root);
    }

    public ArtistDto getArtistByMbid(String mbid) {
        UUID id = requireMbid(mbid);
        rateLimiter.acquire();
        String url = BASE + "/artist/" + id + "?fmt=json";
        JsonNode node = fetchJson(url);
        if (node == null || node.isMissingNode() || node.has("error")) {
            throw new ResourceNotFoundException("Artist not found: " + mbid);
        }
        return mapArtist(node);
    }

    /**
     * Release groups credited to this artist (first page from MusicBrainz).
     */
    public List<AlbumDto> getReleaseGroupsForArtist(String artistMbid) {
        UUID id = requireMbid(artistMbid);
        rateLimiter.acquire();
        String url = BASE + "/artist/" + id + "?fmt=json&inc=release-groups";
        JsonNode node = fetchJson(url);
        if (node == null || node.isMissingNode() || node.has("error")) {
            throw new ResourceNotFoundException("Artist not found: " + artistMbid);
        }
        return dedupeAlbumsById(mapReleaseGroupArray(node.get("release-groups")));
    }

    /**
     * Recordings linked to this release group (album), de-duplicated by recording id.
     */
    public List<SongDto> getRecordingsForReleaseGroup(String releaseGroupMbid) {
        UUID id = requireMbid(releaseGroupMbid);
        rateLimiter.acquire();
        String url = UriComponentsBuilder.fromUriString(BASE + "/recording")
                .queryParam("query", "rgid:" + id)
                .queryParam("fmt", "json")
                .queryParam("limit", 100)
                .encode(StandardCharsets.UTF_8)
                .toUriString();
        JsonNode root = fetchJson(url);
        return dedupeSongsById(mapRecordingSearch(root));
    }

    public List<AlbumDto> sampleAlbums() {
        return searchAlbums("love");
    }

    public List<AlbumDto> searchAlbums(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }
        rateLimiter.acquire();
        String url = UriComponentsBuilder.fromUriString(BASE + "/release-group")
                .queryParam("query", query)
                .queryParam("fmt", "json")
                .queryParam("limit", 25)
                .encode(StandardCharsets.UTF_8)
                .toUriString();
        JsonNode root = fetchJson(url);
        return mapReleaseGroupSearch(root);
    }

    public AlbumDto getAlbumByMbid(String mbid) {
        UUID id = requireMbid(mbid);
        rateLimiter.acquire();
        String url = BASE + "/release-group/" + id + "?fmt=json";
        JsonNode node = fetchJson(url);
        if (node == null || node.isMissingNode() || node.has("error")) {
            throw new ResourceNotFoundException("Album not found: " + mbid);
        }
        return mapReleaseGroup(node);
    }

    public List<SongDto> sampleSongs() {
        return searchSongs("the");
    }

    public List<SongDto> searchSongs(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }
        rateLimiter.acquire();
        String url = UriComponentsBuilder.fromUriString(BASE + "/recording")
                .queryParam("query", query)
                .queryParam("fmt", "json")
                .queryParam("limit", 25)
                .encode(StandardCharsets.UTF_8)
                .toUriString();
        JsonNode root = fetchJson(url);
        return mapRecordingSearch(root);
    }

    public SongDto getSongByMbid(String mbid) {
        UUID id = requireMbid(mbid);
        rateLimiter.acquire();
        String url = BASE + "/recording/" + id + "?fmt=json&inc=artist-credits+releases";
        JsonNode node = fetchJson(url);
        if (node == null || node.isMissingNode() || node.has("error")) {
            throw new ResourceNotFoundException("Song not found: " + mbid);
        }
        return mapRecordingDetail(node);
    }

    private static UUID requireMbid(String mbid) {
        try {
            return UUID.fromString(mbid);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Invalid id: " + mbid);
        }
    }

    private static List<ArtistDto> mapArtistSearch(JsonNode root) {
        if (root == null) {
            return List.of();
        }
        JsonNode artists = root.get("artists");
        if (artists == null || !artists.isArray()) {
            return List.of();
        }
        List<ArtistDto> out = new ArrayList<>();
        for (JsonNode a : artists) {
            ArtistDto dto = mapArtist(a);
            if (dto != null) {
                out.add(dto);
            }
        }
        return out;
    }

    private static ArtistDto mapArtist(JsonNode a) {
        if (a == null || a.isMissingNode()) {
            return null;
        }
        String idStr = text(a, "id");
        String name = text(a, "name");
        if (idStr == null || name == null) {
            return null;
        }
        try {
            return new ArtistDto(UUID.fromString(idStr), name);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static List<AlbumDto> mapReleaseGroupArray(JsonNode groups) {
        if (groups == null || !groups.isArray()) {
            return List.of();
        }
        List<AlbumDto> out = new ArrayList<>();
        for (JsonNode g : groups) {
            AlbumDto dto = mapReleaseGroup(g);
            if (dto != null) {
                out.add(dto);
            }
        }
        return out;
    }

    private static List<AlbumDto> dedupeAlbumsById(List<AlbumDto> albums) {
        Map<UUID, AlbumDto> map = new LinkedHashMap<>();
        for (AlbumDto a : albums) {
            map.putIfAbsent(a.getId(), a);
        }
        return new ArrayList<>(map.values());
    }

    private static List<SongDto> dedupeSongsById(List<SongDto> songs) {
        Map<UUID, SongDto> map = new LinkedHashMap<>();
        for (SongDto s : songs) {
            map.putIfAbsent(s.getId(), s);
        }
        return new ArrayList<>(map.values());
    }

    private static List<AlbumDto> mapReleaseGroupSearch(JsonNode root) {
        if (root == null) {
            return List.of();
        }
        return mapReleaseGroupArray(root.get("release-groups"));
    }

    private static AlbumDto mapReleaseGroup(JsonNode g) {
        if (g == null || g.isMissingNode()) {
            return null;
        }
        String idStr = text(g, "id");
        String title = text(g, "title");
        if (idStr == null || title == null) {
            return null;
        }
        String artistName = firstArtistCreditName(g.get("artist-credit"));
        if (artistName == null) {
            artistName = "Unknown artist";
        }
        try {
            return new AlbumDto(UUID.fromString(idStr), title, artistName);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private static List<SongDto> mapRecordingSearch(JsonNode root) {
        if (root == null) {
            return List.of();
        }
        JsonNode recs = root.get("recordings");
        if (recs == null || !recs.isArray()) {
            return List.of();
        }
        List<SongDto> out = new ArrayList<>();
        for (JsonNode r : recs) {
            SongDto dto = mapRecordingSummary(r);
            if (dto != null) {
                out.add(dto);
            }
        }
        return out;
    }

    private static SongDto mapRecordingSummary(JsonNode r) {
        if (r == null || r.isMissingNode()) {
            return null;
        }
        String idStr = text(r, "id");
        String title = text(r, "title");
        if (idStr == null || title == null) {
            return null;
        }
        UUID id;
        try {
            id = UUID.fromString(idStr);
        } catch (IllegalArgumentException e) {
            return null;
        }
        UUID artistId = parseFirstArtistId(r.get("artist-credit"));
        if (artistId == null) {
            artistId = UNKNOWN_ID;
        }
        return new SongDto(id, title, UNKNOWN_ID, artistId);
    }

    private static SongDto mapRecordingDetail(JsonNode r) {
        SongDto base = mapRecordingSummary(r);
        if (base == null) {
            return null;
        }
        UUID albumId = UNKNOWN_ID;
        JsonNode releases = r.get("releases");
        if (releases != null && releases.isArray()) {
            for (JsonNode rel : releases) {
                JsonNode rg = rel.get("release-group");
                if (rg != null) {
                    String rgId = text(rg, "id");
                    if (rgId != null) {
                        try {
                            albumId = UUID.fromString(rgId);
                            break;
                        } catch (IllegalArgumentException ignored) {
                            // continue
                        }
                    }
                }
            }
        }
        UUID artistId = parseFirstArtistId(r.get("artist-credit"));
        if (artistId == null) {
            artistId = UNKNOWN_ID;
        }
        return new SongDto(base.getId(), base.getTitle(), albumId, artistId);
    }

    private static String text(JsonNode node, String field) {
        if (node == null) {
            return null;
        }
        JsonNode v = node.get(field);
        if (v == null || v.isNull() || !v.isTextual()) {
            return null;
        }
        String s = v.asText();
        return s.isBlank() ? null : s;
    }

    private static String firstArtistCreditName(JsonNode credit) {
        if (credit == null || !credit.isArray()) {
            return null;
        }
        for (JsonNode part : credit) {
            JsonNode artist = part.get("artist");
            if (artist != null) {
                String name = text(artist, "name");
                if (name != null) {
                    return name;
                }
            }
        }
        return null;
    }

    private static UUID parseFirstArtistId(JsonNode credit) {
        if (credit == null || !credit.isArray()) {
            return null;
        }
        for (JsonNode part : credit) {
            JsonNode artist = part.get("artist");
            if (artist != null) {
                String idStr = text(artist, "id");
                if (idStr != null) {
                    try {
                        return UUID.fromString(idStr);
                    } catch (IllegalArgumentException ignored) {
                        // continue
                    }
                }
            }
        }
        return null;
    }
}
