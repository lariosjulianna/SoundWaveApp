import { useEffect, useState } from "react";
import { getRandomSongs, searchSongs } from "../services/songService";
import SongCard from "../components/SongCard";

export default function SongPage() {
  const [songs, setSongs] = useState([]);
  const [query, setQuery] = useState("");
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    loadRandom();
  }, []);

  const loadRandom = async () => {
    try {
      setLoading(true);
      const data = await getRandomASongs();
      setSongs(data);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleSearch = async () => {
    if (!query.trim()) return loadRandom();

    try {
      setLoading(true);
      const results = await searchSongs(query);
      setSongs(results);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: "40px" }}>
      <h1>Songs</h1>

      <div style={{ marginBottom: "20px" }}>
        <input
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          placeholder="Search songs"
        />
        <button onClick={handleSearch}>Search</button>
      </div>

      {loading && <p>Loading...</p>}

      <div style={{ display: "grid", gap: "16px" }}>
        {songs.map((song) => (
          <SongCard key={song.id} song={song} />
        ))}
      </div>
    </div>
  );
}
