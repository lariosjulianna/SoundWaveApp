import { useNavigate } from "react-router-dom";

export default function ArtistCard({ album }) {
  const navigate = useNavigate();

  if (!artist) return null;

  const handleClick = () => {
    navigate(`/artists/${artist.id}/albums`);
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
      <h3>{artist.name}</h3>
  
    </div>
  );
}
