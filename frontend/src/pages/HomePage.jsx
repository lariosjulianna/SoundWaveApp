import { useNavigate } from "react-router-dom";

function Dashboard() {
  const navigate = useNavigate();

  const cardStyle = {
    padding: "30px",
    borderRadius: "12px",
    background: "#1e1e1e",
    color: "white",
    cursor: "pointer",
    textAlign: "center",
    fontSize: "18px",
    fontWeight: "bold",
  };

  return (
    <div style={{ padding: "40px", minHeight: "100vh", background: "#121212" }}>
      <h1 style={{ color: "white" }}>SoundWave Dashboard</h1>

      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(auto-fit, minmax(200px, 1fr))",
          gap: "20px",
          marginTop: "30px",
        }}
      >
        <div style={cardStyle} onClick={() => navigate("/artists")}>
          🎤 Artists
        </div>

        <div style={cardStyle} onClick={() => navigate("/albums")}>
          💿 Albums
        </div>

        <div style={cardStyle} onClick={() => navigate("/songs")}>
          🎵 Songs
        </div>

        <div style={cardStyle} onClick={() => navigate("/feed")}>
          📰 Feed
        </div>
      </div>
    </div>
  );
}

export default Dashboard;
