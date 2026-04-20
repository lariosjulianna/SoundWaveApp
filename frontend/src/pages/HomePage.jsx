// import Feed from "../components/Feed";
// import Sidebar from "../components/Sidebar";
// import DiscoverPanel from "../components/DiscoverPanel";

// function HomePage() {
//   return (
//     <div className="home-container">
//       <Sidebar />
//       <Feed />
//       <DiscoverPanel />
//     </div>
//   );
// }

// export default HomePage;

import HomeCard from "../components/HomeCard";

export default function HomePage() {
  return (
    <div
      style={{
        padding: "40px",
        minHeight: "100vh",
        background: "#121212",
      }}
    >
      <h1 style={{ color: "white" }}>SoundWave Dashboard</h1>

      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(auto-fit, minmax(200px, 1fr))",
          gap: "20px",
          marginTop: "30px",
        }}
      >
        <HomeCard title="Artists" route="/artists" icon="🎤" />
        <HomeCard title="Albums" route="/albums" icon="💿" />
        <HomeCard title="Songs" route="/songs" icon="🎵" />
        <HomeCard title="Feed" route="/posts" icon="📰" />
      </div>
    </div>
  );
}
