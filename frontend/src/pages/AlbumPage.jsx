// import { useEffect, useState } from "react";
// import { useParams } from "react-router-dom";
// import { getAlbumsByArtist } from "../services/albumService";


// export default function AlbumsPage() {
//   const { id } = useParams();
//   const [albums, setAlbums] = useState([]);

//   useEffect(() => {
//     getAlbumsByArtist(artistId)
//       .then(setAlbums)
//       .catch(console.error);
//   }, [artistId]);

//   return (
//     <div>
//       <h1>Albums</h1>

//       {albums.length === 0 && <p>No albums found.</p>}

//       <ul>
//         {albums.map((album) => (
//           <li key={album.id}>{album.title}</li>
//         ))}
//       </ul>
//     </div>
//   );
// }

import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getAlbumsByArtist } from "../services/albumService";

export default function AlbumsPage() {
  const { id } = useParams(); // must match route param
  const [albums, setAlbums] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const fetchAlbums = async () => {
      try {
        setLoading(true);
        const data = await getAlbumsByArtist(id);
        setAlbums(data);
      } catch (error) {
        console.error(error);
      } finally {
        setLoading(false);
      }
    };

    if (id) {
      fetchAlbums();
    }
  }, [id]);

  return (
    <div style={{ padding: "40px" }}>
      <h1>Albums</h1>

      {loading && <p>Loading albums...</p>}

      {!loading && albums.length === 0 && (
        <p>No albums found.</p>
      )}

      <div style={{ display: "grid", gap: "12px" }}>
        {albums.map((album) => (
          <div
            key={album.id}
            style={{
              border: "1px solid #ccc",
              padding: "12px",
              borderRadius: "8px",
            }}
          >
            💿 {album.title}
          </div>
        ))}
      </div>
    </div>
  );
}
