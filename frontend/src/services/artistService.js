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

// need for get artist by Id