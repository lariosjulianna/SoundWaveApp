function PostCard({ post }) {
  return (
    <div className="post-card">
      <h4>@{post.username}</h4>
      <p>{post.content}</p>
      <span>{post.createdAt}</span>
    </div>
  );
}

export default PostCard;
