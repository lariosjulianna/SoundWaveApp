import { useEffect, useState } from "react";

function Feed() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/posts")
      .then(res => res.json())
      .then(data => setPosts(data));
  }, []);

  return (
    <div style={{ padding: "30px" }}>
      <h2>Feed</h2>

      {posts.map(post => (
        <div key={post.id} style={{
          border: "1px solid #ccc",
          padding: "10px",
          marginBottom: "10px",
          borderRadius: "8px"
        }}>
          <p>{post.content}</p>
          <small>{post.topicType}</small>
        </div>
      ))}
    </div>
  );
}

export default Feed;
