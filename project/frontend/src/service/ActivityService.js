import axios from "axios";

const BASE_URL = process.env.REACT_APP_API_BASE_URL + "/activities";
console.log(`API Base URL: ${BASE_URL}`);

export const searchActivities = async (userId, startDate, endDate) => {
  try {
    const response = await axios.get(`${BASE_URL}/search`, {
      params: {
        userId: userId,
        startDate: startDate,
        endDate: endDate,
      },
    });
    console.log("Response from searchActivities:", response.data);
    return response.data;
  } catch (error) {
    console.error(
      "Error fetching activities by user ID and date range:",
      error
    );
    throw error;
  }
};

export const getActivitiesByDateAndUserId = async (date, userId) => {
  try {
    const response = await axios.get(`${BASE_URL}/day`, {
      params: {
        date: date,
        userId: userId,
      },
    });
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error("Error fetching activities by date and user ID:", error);
    throw error;
  }
};

export const getActivityById = async (id) => {
  try {
    const response = await axios.get(`${BASE_URL}/${id}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const createActivity = async (activityData) => {
  try {
    const response = await axios.post(`${BASE_URL}`, activityData);
    return response.data;
  } catch (error) {
    console.error("Error creating entry:", error);
    throw error;
  }
};

export const updateActivity = async (id, details) => {
  try {
    const response = await axios.patch(`${BASE_URL}/${id}`, details);
    return response.data;
  } catch (error) {
    console.error("Error updating activity:", error);
    throw error;
  }
};
export const deleteActivity = async (id) => {
  try {
    const response = await axios.delete(`${BASE_URL}/${id}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};
