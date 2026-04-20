// import { useNavigate } from "react-router-dom";

// export default function HomeCard({ title, route, icon }) {
//   const navigate = useNavigate();

//   return (
//     <div
//       onClick={() => navigate(route)}
//       style={{
//         padding: "30px",
//         borderRadius: "16px",
//         background: "#1e1e1e",
//         color: "white",
//         cursor: "pointer",
//         textAlign: "center",
//         fontSize: "18px",
//         fontWeight: "bold",
//         transition: "0.2s",
//       }}
//     >
//       <div style={{ fontSize: "28px", marginBottom: "10px" }}>
//         {icon}
//       </div>

//       {title}
//     </div>
//   );
// }

import { useNavigate } from "react-router-dom";

export default function HomeCard({ title, route, icon }) {
  const navigate = useNavigate();

  return (
    <div
      onClick={() => navigate(route)}
      style={{
        padding: "30px",
        borderRadius: "16px",
        background: "#1e1e1e",
        color: "white",
        cursor: "pointer",
        textAlign: "center",
        fontSize: "18px",
        fontWeight: "bold",
        transition: "0.2s",
      }}
    >
      <div style={{ fontSize: "28px", marginBottom: "10px" }}>
        {icon}
      </div>

      {title}
    </div>
  );
}
