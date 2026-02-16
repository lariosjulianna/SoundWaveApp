// import { useNavigate } from "react-router-dom";

// export default function AlbumCard({ album }) {
//   const navigate = useNavigate();

//   const handleClick = () => {
//     navigate(`/albums/${album.id}/songs`);
//   };

//   return (
//     <div
//       onClick={handleClick}
//       style={{
//         border: "1px solid #ccc",
//         padding: "12px",
//         marginBottom: "8px",
//         cursor: "pointer",
//       }}
//     >
//       <h3>{album.title}</h3>
//       <p>Release date: {album.releaseDate || "Unknown"}</p>
//     </div>
//   );
// }

import { useNavigate } from "react-router-dom";

export default function AlbumCard({ album }) {
  const navigate = useNavigate();

  if (!album) return null;

  const handleClick = () => {
    navigate(`/albums/${album.id}/songs`);
  };

  return (
    <div
      onClick={handleClick}
      style={{
        border: "1px solid #ccc",
        padding: "12px",
        marginBottom: "8px",
        cursor: "pointer",
        borderRadius: "8px",
      }}
    >
      <h3>{album.title}</h3>
      <p>Artist: {album.artistId || "Unknown"}</p>
    </div>
  );
}
