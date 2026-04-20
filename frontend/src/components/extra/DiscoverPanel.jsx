import { useEffect, useState } from "react";
import { getFeed } from "../../services/homeService";

function DiscoverPanel() {
  const [artists, setArtists] = useState([]);

  useEffect(() => {
    async function loadData() {
      const data = await getFeed();
      setArtists(data.artists);
    }

    loadData();
  }, []);

  return (
    <div className="discover-panel">
      <h3>Trending Artists</h3>

      {artists.map(artist => (
        <div key={artist.id}>
          {artist.name}
        </div>
      ))}
    </div>
  );
}

export default DiscoverPanel;
