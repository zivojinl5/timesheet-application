import React, { useState, useEffect, useCallback } from "react";
import MonthNavigator from "../components/nav/MonthNavigator";
import MonthTable from "../components/tables/MonthTable";
import { searchActivities } from "../service/ActivityService";

export default function MonthPage() {
  const [currentMonth, setCurrentMonth] = useState(new Date().getMonth()); // Start with the current month
  const [activitiesData, setActivitiesData] = useState(null);
  const userId = 1;

  const currentDate = new Date();

  const startDate = new Date(
    currentDate.getFullYear(),
    currentDate.getMonth(),
    1
  )
    .toISOString()
    .split("T")[0]; // Format as yyyy-MM-dd

  const endDate = new Date(
    currentDate.getFullYear(),
    currentDate.getMonth() + 1,
    0
  )
    .toISOString()
    .split("T")[0]; // Format as yyyy-MM-dd

  const fetchActivities = useCallback(async () => {
    try {
      const data = await searchActivities(userId, startDate, endDate);
      setActivitiesData(data);
    } catch (err) {
      console.error("Error fetching activities:", err);
    }
  }, [userId, startDate, endDate]);

  useEffect(() => {
    fetchActivities();
  }, [fetchActivities]);

  if (!activitiesData) {
    return <div>Loading...</div>;
  }

  const { dateHoursMap, totalHours } = activitiesData;

  return (
    <div className="container">
      <MonthNavigator
        currentMonth={currentMonth}
        setCurrentMonth={setCurrentMonth}
      />
      <MonthTable
        currentMonth={currentMonth}
        startDate={startDate}
        dateHoursMap={dateHoursMap}
      />
      <div style={{ marginTop: "20px", fontWeight: "bold" }}>
        Total Hours: {totalHours}
      </div>
    </div>
  );
}
