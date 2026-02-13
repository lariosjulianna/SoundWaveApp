
// export default function ArtistCard({ artist }) {
//   return (
//     <div style={{ border: "1px solid #ccc", padding: "8px" }}>
//       <h3>{artist.name}</h3>
//       <p>ID: {artist.id}</p>
//     </div>
//   );
// }

import { useNavigate } from "react-router-dom";

export default function ArtistCard({ artist }) {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/artists/${artist.id}/albums`);
  };

  return (
    <div
      onClick={handleClick}
      style={{
        border: "1px solid #ccc",
        padding: "8px",
        cursor: "pointer",
      }}
    >
      <h3>{artist.name}</h3>
    </div>
  );
}
