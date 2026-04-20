import { useEffect, useState } from "react";
import { getRandomPosts, searchPosts } from "../services/feedService";
import FeedCard from "../components/FeedCard";

export default function FeedPage() {
  const [posts, setPosts] = useState([]);
  const [query, setQuery] = useState("");
  const [loading, setLoading] = useState(false);

    useEffect(() => {
      // fetch("http://localhost:8080/posts")
      //   .then(res => res.json())
      //   .then(data => setPosts(data));
      loadRandom();
    }, []);

    const loadRandom = async () => {
        try {
          setLoading(true);
          const data = await getRandomPosts();
          console.log("ACTUAL BACKEND RESPONSE:", data);
          setPosts(data);
        } catch (err) {
          console.error(err);
        } finally {
          setLoading(false);
        }
      };

      const handleSearch = async () => {
          if (!query.trim()) return loadRandom();
      
          try {
            setLoading(true);
            const results = await searchPosts(query);
            setPosts(results);
          } catch (err) {
            console.error(err);
          } finally {
            setLoading(false);
          }
        };
      

    // return (
    //   <div style={{ padding: "30px" }}>
    //     <h2>Feed</h2>

    //     {posts.map(post => (
    //       <div key={post.id} style={{
    //         border: "1px solid #ccc",
    //         padding: "10px",
    //         marginBottom: "10px",
    //         borderRadius: "8px"
    //       }}>
    //         <p>{post.content}</p>
    //         <small>{post.topicType}</small>
    //       </div>
    //     ))}
    //   </div>
    // );
        return (
            <div style={{ padding: "40px" }}>
              <h1>Posts</h1>
        
              <div style={{ marginBottom: "20px" }}>
                <input
                  value={query}
                  onChange={(e) => setQuery(e.target.value)}
                  placeholder="Search posts"
                />
                <button onClick={handleSearch}>Search</button>
              </div>
        
              {loading && <p>Loading...</p>}
        
              <div style={{ display: "grid", gap: "16px" }}>
                {posts.map((post) => (
                  <FeedCard key={post.id} post={post} />
                ))}
              </div>
            </div>
          );
  }

