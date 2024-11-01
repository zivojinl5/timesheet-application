import axios from "axios";

const BASE_URL = process.env.REACT_APP_API_BASE_URL + "/users";

export const getTeamMembers = async (
  pageNumber,
  pageSize,
  sortingProperty,
  sortingDirection
) => {
  try {
    const response = await axios.get(
      `${BASE_URL}/pagination/${pageNumber}/${pageSize}/${sortingProperty}/${sortingDirection}`
    );
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};

export const createTeamMember = async (teamMember) => {
  try {
    const response = await axios.post(`${BASE_URL}/register`, teamMember);
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};

export const updateTeamMember = async (id, details) => {
  try {
    const response = await axios.patch(`${BASE_URL}/${id}`, details);
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};

export const deleteTeamMember = async (id) => {
  try {
    const response = await axios.delete(`${BASE_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};
