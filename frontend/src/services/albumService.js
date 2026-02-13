const API_BASE_URL = "http://localhost:8080/albums";

export async function getAlbumsByArtist(artistId) {
  const response = await fetch(`${API_BASE_URL}/artist/${artistId}`);
  if (!response.ok) {
    throw new Error("Failed to fetch albums");
  }
  return response.json();
}
