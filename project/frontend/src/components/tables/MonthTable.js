import React from "react";
import MonthTableCell from "./MonthTableCell";

export default function Table({ currentMonth, dateHoursMap }) {
  const columns = 7;
  const daysOfWeek = [
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
    "Sunday",
  ];

  const currentDate = new Date();
  const currentYear = currentDate.getFullYear();

  const firstDayOfMonth = new Date(currentYear, currentMonth, 1);
  // Get the total number of days in the current month
  const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();

  // Array to store the date for each cell
  const cells = [];

  // Calculate the day of the week for the 1st of the month (0 = Sunday, 6 = Saturday)
  const firstDayIndex = (firstDayOfMonth.getDay() + 6) % 7; // Adjust to start from Monday

  // Fill in the table cells with dates
  let dayCount = 1;
  for (let i = 0; i < 6; i++) {
    const row = [];
    for (let j = 0; j < columns; j++) {
      if (i === 0 && j < firstDayIndex) {
        row.push(null); // Empty cell before the first day of the month
      } else if (dayCount <= daysInMonth) {
        row.push(dayCount);
        dayCount++;
      } else {
        row.push(null); // Empty cell after the last day of the month
      }
    }
    cells.push(row);
  }

  return (
    <div className="container-fluid">
      <table className="table table-bordered w-100">
        <thead>
          <tr>
            {daysOfWeek.map((day, index) => (
              <th key={index} className="text-center">
                {day}
              </th>
            ))}
          </tr>
        </thead>
        <tbody>
          {cells.map((row, rowIndex) => (
            <tr key={rowIndex}>
              {row.map((cell, colIndex) => {
                const day = cell ? cell : null;
                const formattedDate = day
                  ? `${currentYear}-${(currentMonth + 1)
                      .toString()
                      .padStart(2, "0")}-${day.toString().padStart(2, "0")}`
                  : null;

                const hoursWorked = formattedDate
                  ? dateHoursMap[formattedDate] || 0
                  : 0;

                return (
                  <MonthTableCell
                    key={colIndex}
                    day={day}
                    fullDate={formattedDate}
                    hoursWorked={hoursWorked}
                  />
                );
              })}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
