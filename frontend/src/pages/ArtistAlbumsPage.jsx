import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { getArtistById, getAlbumsForArtist } from "../services/artistService";
import AlbumCard from "../components/AlbumCard";

export default function ArtistAlbumsPage() {
  const { artistId } = useParams();
  const [artist, setArtist] = useState(null);
  const [albums, setAlbums] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    let cancelled = false;

    async function load() {
      setLoading(true);
      setError(null);
      try {
        const [a, list] = await Promise.all([
          getArtistById(artistId),
          getAlbumsForArtist(artistId),
        ]);
        if (!cancelled) {
          setArtist(a);
          setAlbums(Array.isArray(list) ? list : []);
        }
      } catch (e) {
        if (!cancelled) {
          setError(e.message || "Could not load artist");
          setArtist(null);
          setAlbums([]);
        }
      } finally {
        if (!cancelled) setLoading(false);
      }
    }

    load();
    return () => {
      cancelled = true;
    };
  }, [artistId]);

  return (
    <div style={{ padding: "40px" }}>
      <p style={{ marginBottom: "16px" }}>
        <Link to="/artists">← Artists</Link>
      </p>

      {loading && <p>Loading…</p>}
      {error && <p style={{ color: "#c00" }}>{error}</p>}

      {!loading && !error && artist && (
        <>
          <h1 style={{ marginBottom: "8px" }}>{artist.name}</h1>
          <p style={{ color: "#555", marginBottom: "24px" }}>Albums</p>

          {albums.length === 0 ? (
            <p>No albums returned for this artist.</p>
          ) : (
            <div style={{ display: "grid", gap: "16px" }}>
              {albums.map((album) => (
                <AlbumCard key={album.id} album={album} />
              ))}
            </div>
          )}
        </>
      )}
    </div>
  );
}
