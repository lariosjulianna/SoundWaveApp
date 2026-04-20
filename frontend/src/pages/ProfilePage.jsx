import { Link } from "react-router-dom";

export default function ProfilePage() {
  return (
    <div style={{ padding: "40px" }}>
      <p style={{ marginBottom: "16px" }}>
        <Link to="/">← Home</Link>
      </p>
      <h1>Profile</h1>
      <p style={{ color: "#555" }}>This page is a placeholder for a future profile screen.</p>
    </div>
  );
}
