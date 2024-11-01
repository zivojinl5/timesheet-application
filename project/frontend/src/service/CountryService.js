import axios from "axios";

const BASE_URL = process.env.REACT_APP_API_BASE_URL + "/countries";

export const getCountriesWithPagination = async (
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
export const getCountries = async () => {
  try {
    const response = await axios.get(`${BASE_URL}`);
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};
export const createCountry = async (country) => {
  try {
    const response = await axios.post(`${BASE_URL}`, country);
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};

export const getCountryById = async (id) => {
  try {
    const response = await axios.get(`${BASE_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};

export const updateCountry = async (id, details) => {
  try {
    const response = await axios.patch(`${BASE_URL}/${id}`, details);
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};

export const deleteCountry = async (id) => {
  try {
    const response = await axios.delete(`${BASE_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};
