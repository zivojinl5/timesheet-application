import axios from "axios";

const BASE_URL = process.env.REACT_APP_API_BASE_URL + "/projects";

export const getProjects = async () => {
  try {
    const response = await axios.get(`${BASE_URL}`);
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};
export const getProjectsByClientId = async (clientId) => {
  try {
    const response = await axios.get(`${BASE_URL}/client/${clientId}`);
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.error("Error fetching projects:", error.response);
    throw error;
  }
};
