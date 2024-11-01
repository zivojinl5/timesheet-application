import axios from "axios";

const BASE_URL = process.env.REACT_APP_API_BASE_URL + "/clients";

export const getClientsWithPagination = async (
  pageNumber,
  pageSize,
  sortingProperty,
  sortingDirection
) => {
  try {
    const response = await axios.get(
      `${BASE_URL}/pagination/${pageNumber}/${pageSize}/${sortingProperty}/${sortingDirection}`
    );
    return response.data;
  } catch (error) {
    console.error("Error fetching clients:", error);
    throw error;
  }
};

export const getClients = async () => {
  try {
    const response = await axios.get(`${BASE_URL}`);
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};
export const searchClients = async (
  startingLetter,
  pageNumber,
  pageSize,
  sortingProperty,
  sortingDirection,
  searchString
) => {
  try {
    const response = await axios.get(
      `${BASE_URL}/search/${startingLetter}/${pageNumber}/${pageSize}/${sortingProperty}/${sortingDirection}`,
      {
        params: {
          searchString: searchString || null, // Ensuring searchString is either sent as a value or null if it's undefined
        },
      }
    );
    return response.data;
  } catch (error) {
    console.error("Error searching clients:", error);
    throw error;
  }
};

export const getAllStartingLetters = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/startingLetters`);
    return response.data;
  } catch (error) {
    console.error("Error fetching starting letters:", error);
    throw error;
  }
};

export const createClient = async (client) => {
  try {
    const response = await axios.post(`${BASE_URL}`, client);
    return response.data;
  } catch (error) {
    console.error("Error creating client:", error);
    throw error;
  }
};

export const getClientById = async (id) => {
  try {
    const response = await axios.get(`${BASE_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.error("Error fetching client by ID:", error);
    throw error;
  }
};

export const updateClient = async (id, details) => {
  try {
    const response = await axios.patch(`${BASE_URL}/${id}`, details);
    return response.data;
  } catch (error) {
    console.error("Error updating client:", error);
    throw error;
  }
};

export const deleteClient = async (id) => {
  try {
    const response = await axios.delete(`${BASE_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.error("Error deleting client:", error);
    throw error;
  }
};
