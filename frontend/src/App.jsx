// import ArtistsPage from "./pages/ArtistsPage";

// function App() {
//   return (
//     <div style={{ padding: "40px" }}>
//       <ArtistsPage />
//     </div>
//   );
// }

// export default App;


import { Routes, Route } from "react-router-dom";
import ArtistPage from "./pages/ArtistPage";
//import AlbumsPage from "./pages/AlbumPage";

// function App() {
//   return (
//     <BrowserRouter>
//       <Routes>
//         <Route path="/" element={<ArtistPage />} />
//         <Route path="/artists/:artistId/albums" element={<AlbumsPage />} />
//       </Routes>
//     </BrowserRouter>
//   );
// }
function App() {
  return (
    <Routes>
      <Route path="/" element={<ArtistPage />} />
    </Routes>
  );
}

export default App;
