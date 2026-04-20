import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { getAlbumById, getSongsForAlbum } from "../services/albumService";
import SongCard from "../components/SongCard";

export default function AlbumSongsPage() {
  const { albumId } = useParams();
  const [album, setAlbum] = useState(null);
  const [songs, setSongs] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    let cancelled = false;

    async function load() {
      setLoading(true);
      setError(null);
      try {
        const [a, list] = await Promise.all([
          getAlbumById(albumId),
          getSongsForAlbum(albumId),
        ]);
        if (!cancelled) {
          setAlbum(a);
          setSongs(Array.isArray(list) ? list : []);
        }
      } catch (e) {
        if (!cancelled) {
          setError(e.message || "Could not load album");
          setAlbum(null);
          setSongs([]);
        }
      } finally {
        if (!cancelled) setLoading(false);
      }
    }

    load();
    return () => {
      cancelled = true;
    };
  }, [albumId]);

  return (
    <div style={{ padding: "40px" }}>
      <p style={{ marginBottom: "16px" }}>
        <Link to="/albums">← Albums</Link>
      </p>

      {loading && <p>Loading…</p>}
      {error && <p style={{ color: "#c00" }}>{error}</p>}

      {!loading && !error && album && (
        <>
          <h1 style={{ marginBottom: "8px" }}>{album.title}</h1>
          <p style={{ color: "#555", marginBottom: "24px" }}>
            {album.artistName} · Tracks
          </p>

          {songs.length === 0 ? (
            <p>No recordings returned for this album.</p>
          ) : (
            <div style={{ display: "grid", gap: "16px" }}>
              {songs.map((song) => (
                <SongCard key={song.id} song={song} />
              ))}
            </div>
          )}
        </>
      )}
    </div>
  );
}
