import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import { getRandomArtists, searchArtists } from "../services/artistService";
import ArtistCard from "../components/ArtistCard";

export default function ArtistPage() {
  const [artists, setArtists] = useState([]);
  const [query, setQuery] = useState("");

  useEffect(() => {
    getRandomArtists()
      .then(setArtists)
      .catch(console.error);
  }, []);

  const handleSearch = async () => {
    const results = await searchArtists(query);
    setArtists(results);
  };

  return (
    <div>
      <h1>Artists</h1>

      <input
        value={query}
        onChange={(e) => setQuery(e.target.value)}
        placeholder="Search artists"
      />
      <button onClick={handleSearch}>Search</button>

      <div>
        {artists.map((artist) => (
          <ArtistCard key={artist.id} artist={artist} />
        ))}
      </div>
    </div>
  );
}



// import { useEffect, useState } from "react";
// import ArtistCard from "../components/ArtistCard";

// function ArtistsPage() {
//   const [artists, setArtists] = useState([]);
//   const [albums, setAlbums] = useState([]);
//   const [selectedArtist, setSelectedArtist] = useState(null);
//   const [songs, setSongs] = useState([]);

//   useEffect(() => {
//     fetch("http://localhost:8080/api/artists/random")
//       .then(res => res.json())
//       .then(data => setArtists(data));
//   }, []);

//   const fetchAlbums = (artist) => {
//     setSelectedArtist(artist);
//     setAlbums([]);
//     setSongs([]);

//     fetch(`http://localhost:8080/api/artists/${artist.id}/albums`)
//       .then(res => res.json())
//       .then(data => setAlbums(data));
//   };

//   const fetchSongs = (albumId) => {
//     fetch(`http://localhost:8080/api/artists/albums/${albumId}/songs`)
//       .then(res => res.json())
//       .then(data => setSongs(data));
//   };

//   return (
//     <>
//       <h1>Artists</h1>

//       {artists.map(artist => (
//         <ArtistCard
//           key={artist.id}
//           artist={artist}
//           onClick={fetchAlbums}
//         />
//       ))}

//       {selectedArtist && (
//         <>
//           <h2>Albums by {selectedArtist.name}</h2>

//           {albums.map((album, index) => (
//             <div
//               key={index}
//               style={{ cursor: "pointer" }}
//               onClick={() => fetchSongs(index)}
//             >
//               💿 {album}
//             </div>
//           ))}
//         </>
//       )}

//       {songs.length > 0 && (
//         <>
//           <h3>Songs</h3>
//           {songs.map((song, i) => (
//             <div key={i}>🎵 {song}</div>
//           ))}
//         </>
//       )}
//     </>
//   );
// }

// export default ArtistsPage;







// return (
//   <>
//     <div>
//       <h1>Artists</h1>

//       {artists.map(artist => (
//         <ArtistCard
//           key={artist.id}
//           artist={artist}
//           onClick={fetchAlbums}
//         />
//       ))}

//       {selectedArtist && (
//         <>
//           <h2>Albums by {selectedArtist.name}</h2>
//           {albums.map((album, index) => (
//             <div
//               key={index}
//               style={{ cursor: "pointer" }}
//               onClick={() => fetchSongs(index)}
//             >
//               💿 {album}
//             </div>
//           ))}
//         </>
//       )}
//     </div>

//     {songs.length > 0 && (
//       <>
//         <h3>Songs</h3>
//         {songs.map((song, i) => (
//           <div key={i}>🎵 {song}</div>
//         ))}
//       </>
//     )}
//   </>
// );
