// import ArtistsPage from "./pages/ArtistsPage";

// function App() {
//   return (
//     <div style={{ padding: "40px" }}>
//       <ArtistsPage />
//     </div>
//   );
// }

// export default App;

/**
 * App.jsx controls routing
 */

import { BrowserRouter, Routes, Route } from "react-router-dom";

//import HomePage from "./pages/HomePage";
import ArtistPage from "./pages/ArtistPage";
//import AlbumPage from "./pages/AlbumPage";
// import SongPage from "./pages/SongPage";
//import FeedPage from "./pages/FeedPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
  
        <Route path="/artists" element={<ArtistPage />} />

      </Routes>
    </BrowserRouter>
  );
}

 export default App;


 /**
  * 
  * <Route path="/albums" element={<AlbumPage />} />
        <Route path="/songs" element={<SongPage />} />
        <Route path="/feed" element={<FeedPage />} />
          <Route path="/" element={<HomePage />} />
  */