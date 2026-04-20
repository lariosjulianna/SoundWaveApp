import { BrowserRouter, Routes, Route } from "react-router-dom";

import ArtistPage from "./pages/ArtistPage";
import ArtistAlbumsPage from "./pages/ArtistAlbumsPage";
import AlbumPage from "./pages/AlbumPage";
import AlbumSongsPage from "./pages/AlbumSongsPage";
import SongPage from "./pages/SongPage";
import SongDetailPage from "./pages/SongDetailPage";
import FeedPage from "./pages/FeedPage";
import HomePage from "./pages/HomePage";
import ProfilePage from "./pages/ProfilePage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/artists/:artistId/albums" element={<ArtistAlbumsPage />} />
        <Route path="/artists" element={<ArtistPage />} />
        <Route path="/albums/:albumId/songs" element={<AlbumSongsPage />} />
        <Route path="/albums" element={<AlbumPage />} />
        <Route path="/songs/:songId" element={<SongDetailPage />} />
        <Route path="/songs" element={<SongPage />} />
        <Route path="/posts" element={<FeedPage />} />
        <Route path="/profile" element={<ProfilePage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
