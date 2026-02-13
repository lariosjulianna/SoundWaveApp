import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getAlbumsByArtist } from "../services/albumService";


export default function AlbumsPage() {
  const { artistId } = useParams();
  const [albums, setAlbums] = useState([]);

  useEffect(() => {
    getAlbumsByArtist(artistId)
      .then(setAlbums)
      .catch(console.error);
  }, [artistId]);

  return (
    <div>
      <h1>Albums</h1>

      {albums.length === 0 && <p>No albums found.</p>}

      <ul>
        {albums.map((album) => (
          <li key={album.id}>{album.title}</li>
        ))}
      </ul>
    </div>
  );
}
