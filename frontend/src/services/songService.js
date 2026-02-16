const API_BASE_URL = "http://localhost:8080/songs";

export async function getRandomSongs() {
  const response = await fetch(`${API_BASE_URL}/random`);
  if (!response.ok) {
    throw new Error("Failed to fetch songs");
  }
  return response.json();
}

export async function searchSongs(query) {
  const response = await fetch(
    `${API_BASE_URL}/search?q=${encodeURIComponent(query)}`
  );
  if (!response.ok) {
    throw new Error("Failed to search songs");
  }
  return response.json();
}

// need for get song by Id