import React from "react";

export default function MonthNavigator({ currentMonth, setCurrentMonth }) {
  const monthNames = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
  ];

  const handlePrevMonth = () => {
    setCurrentMonth((prev) => (prev === 0 ? 11 : prev - 1)); // Set to December if at January
  };

  const handleNextMonth = () => {
    setCurrentMonth((prev) => (prev === 11 ? 0 : prev + 1)); // Set to January if at December
  };

  return (
    <div className="d-flex justify-content-between align-items-center my-3">
      <button className="btn btn-primary" onClick={handlePrevMonth}>
        &lt; Previous
      </button>
      <h2>{monthNames[currentMonth]}</h2>
      <button className="btn btn-primary" onClick={handleNextMonth}>
        Next &gt;
      </button>
    </div>
  );
}
