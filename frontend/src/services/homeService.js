const API_BASE_URL = "http://localhost:8080/home";

export async function getFeed() {
  const response = await fetch(`${API_BASE_URL}/feed`);

  if (!response.ok) {
    throw new Error("Failed to fetch feed");
  }

  return response.json();
}
