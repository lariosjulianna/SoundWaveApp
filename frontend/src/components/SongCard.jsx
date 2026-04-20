import { useNavigate } from "react-router-dom";

export default function SongCard({ song }) {
  const navigate = useNavigate();

  if (!song) return null;

  const handleClick = () => {
    navigate(`/songs/${song.id}`);
  };

  return (
    <div
      onClick={handleClick}
      style={{
        border: "1px solid #ddd",
        padding: "12px",
        marginBottom: "8px",
        borderRadius: "8px",
        cursor: "pointer",
        transition: "0.2s",
      }}
    >
      <h4>🎵 {song.title}</h4>

      <p style={{ margin: 0 }}>
        Artist: {song.artistId || "Unknown"}
      </p>

      {song.albumTitle && (
        <small>Album: {song.albumTitle}</small>
      )}
    </div>
  );
}
