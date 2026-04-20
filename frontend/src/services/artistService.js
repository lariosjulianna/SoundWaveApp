const API_BASE_URL = "http://localhost:8080/artists";

export async function getRandomArtists() {
  const response = await fetch(`${API_BASE_URL}/random`);
  if (!response.ok) {
    throw new Error("Failed to fetch artists");
  }
  return response.json();
}

export async function searchArtists(query) {
  const response = await fetch(
    `${API_BASE_URL}/search?q=${encodeURIComponent(query)}`
  );
  if (!response.ok) {
    throw new Error("Failed to search artists");
  }
  return response.json();
}

export async function getArtistById(artistId) {
  const response = await fetch(`${API_BASE_URL}/${encodeURIComponent(artistId)}`);
  if (!response.ok) {
    throw new Error("Failed to fetch artist");
  }
  return response.json();
}

export async function getAlbumsForArtist(artistId) {
  const response = await fetch(
    `${API_BASE_URL}/${encodeURIComponent(artistId)}/albums`
  );
  if (!response.ok) {
    throw new Error("Failed to fetch artist albums");
  }
  return response.json();
}
