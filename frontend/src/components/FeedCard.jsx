import { useNavigate } from "react-router-dom";

export default function FeedCard({ post }) {
  const navigate = useNavigate();

  if (!post) return null;

  const handleClick = () => {
    if (post.topicType === "ARTIST") {
      navigate(`/artists/${post.topicId}/albums`);
    } else if (post.topicType === "ALBUM") {
      navigate(`/albums/${post.topicId}/songs`);
    } else if (post.topicType === "SONG") {
      navigate(`/songs/${post.topicId}`);
    }
  };

  return (
    <div
      onClick={handleClick}
      style={{
        border: "1px solid #ddd",
        padding: "16px",
        marginBottom: "12px",
        borderRadius: "10px",
        cursor: "pointer",
        background: "#f9f9f9"
      }}
    >
      <p style={{ fontWeight: "bold" }}>
        {post.username || "User"}
      </p>

      <p>{post.content}</p>
      
      <small>
        {post.topicName} ({post.topicType})
      </small>
    </div>
  );
}

// import { useNavigate } from "react-router-dom";

// export default function FeedCard({ post }) {
//   const navigate = useNavigate();

//   if (!post) return null;

//   useEffect(() => {
//   async function loadFeed() {
//     const data = await getFeed();
//     console.log("FEED DATA:", data);
//     setPosts(data);
//   }
//   loadFeed();
// }, []);


//   const handleClick = () => {
//     if (post.topicType === "ARTIST") {
//       navigate(`/artists/${post.topicId}/albums`);
//     } else if (post.topicType === "ALBUM") {
//       navigate(`/albums/${post.topicId}/songs`);
//     } else if (post.topicType === "SONG") {
//       navigate(`/songs/${post.topicId}`);
//     }
//   };

//   return (
//     <div
//       onClick={handleClick}
//       style={{
//         border: "1px solid #ddd",
//         padding: "16px",
//         marginBottom: "12px",
//         borderRadius: "10px",
//         cursor: "pointer",
//         background: "#f9f9f9"
//       }}
//     >
//       <p style={{ fontWeight: "bold" }}>
//         {post.username || "User"}
//       </p>

//       <p>{post.content}</p>

//       <small>
//         About: {post.topicType}
//       </small>
//     </div>
//   );
// }
