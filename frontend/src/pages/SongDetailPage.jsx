import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { getSongById } from "../services/songService";

const ZERO_UUID = "00000000-0000-0000-0000-000000000000";

function hasRealUuid(value) {
  return (
    value != null &&
    String(value).length > 0 &&
    String(value).toLowerCase() !== ZERO_UUID
  );
}

export default function SongDetailPage() {
  const { songId } = useParams();
  const [song, setSong] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    let cancelled = false;

    async function load() {
      setLoading(true);
      setError(null);
      try {
        const data = await getSongById(songId);
        if (!cancelled) setSong(data);
      } catch (e) {
        if (!cancelled) {
          setError(e.message || "Could not load song");
          setSong(null);
        }
      } finally {
        if (!cancelled) setLoading(false);
      }
    }

    load();
    return () => {
      cancelled = true;
    };
  }, [songId]);

  return (
    <div style={{ padding: "40px", maxWidth: "560px" }}>
      <p style={{ marginBottom: "16px" }}>
        <Link to="/songs">← Songs</Link>
      </p>

      {loading && <p>Loading…</p>}
      {error && <p style={{ color: "#c00" }}>{error}</p>}

      {!loading && !error && song && (
        <>
          <h1 style={{ marginBottom: "16px" }}>{song.title}</h1>
          <dl style={{ lineHeight: 1.8 }}>
            <dt style={{ fontWeight: 600 }}>Recording id</dt>
            <dd style={{ margin: "0 0 12px 0" }}>{song.id}</dd>
            {hasRealUuid(song.artistId) && (
              <>
                <dt style={{ fontWeight: 600 }}>Artist</dt>
                <dd style={{ margin: "0 0 12px 0" }}>
                  <Link to={`/artists/${song.artistId}/albums`}>
                    Open artist albums
                  </Link>
                </dd>
              </>
            )}
            {hasRealUuid(song.albumId) && (
              <>
                <dt style={{ fontWeight: 600 }}>Album</dt>
                <dd style={{ margin: 0 }}>
                  <Link to={`/albums/${song.albumId}/songs`}>
                    Open album track list
                  </Link>
                </dd>
              </>
            )}
          </dl>
        </>
      )}
    </div>
  );
}
