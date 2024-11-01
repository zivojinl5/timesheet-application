import React from "react";
import { Link } from "react-router-dom";

export default function TableCell({ day, fullDate, hoursWorked }) {
  let backgroundColor;

  if (hoursWorked === 0) {
    backgroundColor = "red"; // Color for 0 hours
  } else if (hoursWorked > 8) {
    backgroundColor = "orange"; // Overtime color
  } else {
    backgroundColor = "green"; // Normal hours color
  }

  return (
    <td
      style={{
        position: "relative",
        textAlign: "center",
        height: "100px",
        border: "2px solid black", // Thicker borders between cells
        padding: 0,
      }}
    >
      {day ? (
        <>
          {/* Top half as a clickable link using the full date */}
          <Link
            to={`/day/${fullDate}`} // Use full date for the link
            style={{
              display: "block",
              width: "100%",
              height: "50%",
              textDecoration: "none",
              color: "inherit",
              position: "relative",
              paddingTop: "5px",
              zIndex: 1,
            }}
          >
            {day} {/* Display only the day */}
          </Link>

          {/* Bottom half showing hours */}
          <div
            style={{
              position: "absolute",
              bottom: 0,
              left: 0,
              right: 0,
              height: "50%",
              backgroundColor: backgroundColor,
              color: "black",
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
              zIndex: 0,
              borderTop: "2px solid black", // Border between date and hours
            }}
          >
            {`${hoursWorked}h`} {/* Display hours below the date */}
          </div>
        </>
      ) : (
        ""
      )}
    </td>
  );
}
