import { Link } from "react-router-dom";

function Sidebar() {
  return (
    <div className="sidebar">
      <h2>SoundWave</h2>

      <nav>
        <Link to="/">Home</Link>
        <Link to="/artists">Artists</Link>
        <Link to="/albums">Albums</Link>
        <Link to="/songs">Songs</Link>
        <Link to="/profile">Profile</Link>
      </nav>
    </div>
  );
}

export default Sidebar;
