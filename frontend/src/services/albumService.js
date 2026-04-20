const API_BASE_URL = "http://localhost:8080/albums";

export async function getRandomAlbums() {
  const response = await fetch(`${API_BASE_URL}/random`);
  if (!response.ok) {
    throw new Error("Failed to fetch albums");
  }
  return response.json();
}

export async function searchAlbums(query) {
  const response = await fetch(
    `${API_BASE_URL}/search?q=${encodeURIComponent(query)}`
  );
  if (!response.ok) {
    throw new Error("Failed to search albums");
  }
  return response.json();
}

export async function getAlbumById(albumId) {
  const response = await fetch(`${API_BASE_URL}/${encodeURIComponent(albumId)}`);
  if (!response.ok) {
    throw new Error("Failed to fetch album");
  }
  return response.json();
}

export async function getSongsForAlbum(albumId) {
  const response = await fetch(
    `${API_BASE_URL}/${encodeURIComponent(albumId)}/songs`
  );
  if (!response.ok) {
    throw new Error("Failed to fetch album songs");
  }
  return response.json();
}
