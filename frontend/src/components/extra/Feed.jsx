import { useEffect, useState } from "react";
import { getFeed } from "../../services/homeService";
import PostCard from "./PostCard";

function Feed() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    async function loadFeed() {
      const data = await getFeed();
      setPosts(data.posts);
    }

    loadFeed();
  }, []);

  return (
    <div className="feed">
      <h2>Home</h2>

      {posts.map(post => (
        <PostCard key={post.id} post={post} />
      ))}
    </div>
  );
}

export default Feed;
