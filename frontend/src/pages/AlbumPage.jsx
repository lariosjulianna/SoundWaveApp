import { useEffect, useState } from "react";
import { getRandomAlbums, searchAlbums } from "../services/albumService";
import AlbumCard from "../components/AlbumCard";

export default function AlbumPage() {
  const [albums, setAlbums] = useState([]);
  const [query, setQuery] = useState("");
  const [loading, setLoading] = useState(false);

  useEffect(() => {
      loadRandom();
    }, []);
  
    const loadRandom = async () => {
      try {
        setLoading(true);
        const data = await getRandomAlbums();
        setAlbums(data);
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
        const results = await searchAlbums(query);
        setAlbums(results);
      } catch (err) {
        console.error(err);
      } finally {
        setLoading(false);
      }
    };
  
    return (
      <div style={{ padding: "40px" }}>
        <h1>Albums</h1>
  
        <div style={{ marginBottom: "20px" }}>
          <input
            value={query}
            onChange={(e) => setQuery(e.target.value)}
            placeholder="Search albums"
          />
          <button onClick={handleSearch}>Search</button>
        </div>
  
        {loading && <p>Loading...</p>}

        <div style={{ display: "grid", gap: "16px" }}>
          {albums.map((album) => (
            <AlbumCard key={album.id} album={album} />
          ))}
        </div>
      </div>
    );
  }
  