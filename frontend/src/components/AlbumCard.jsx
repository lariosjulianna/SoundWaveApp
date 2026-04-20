// React router hook -- for programmatic navigation
import { useNavigate } from "react-router-dom";

/**
 * AlbumCard Component
 * 
 * Displays a clickable card for single albums
 * When clicked, sends user to album's songs pages (TBD)
 *
 */

export default function AlbumCard({ album }) {
  // Hook that allows navigation without <Link>
  const navigate = useNavigate();


  // prevents rendering if there is no album data
  if (!album) return null;


  /**
   * Handles card click event
   * Navigate to album's songs route using albumId
   */

  const handleClick = () => {
    navigate(`/albums/${album.id}/songs`);
  };

  return (
    <div
      onClick={handleClick} // makes entire card clickable
      style={{
        border: "1px solid #ccc",
        padding: "12px",
        marginBottom: "8px",
        cursor: "pointer",
        borderRadius: "8px",
      }}
    >
      {/* Album title */}
      <h3>{album.title}</h3>

      {/* Displays associated artist or fallback if missing */}
      <p>Artist: {album.artist?.name || "Unknown"}</p>
    </div>
  );
}
