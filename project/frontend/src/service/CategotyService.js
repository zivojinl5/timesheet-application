import axios from "axios";

const BASE_URL = process.env.REACT_APP_API_BASE_URL + "/categories";
console.log(`API Base URL categories: ${BASE_URL}`);
export const getCategoriesWithPagination = async (
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
export const getCategories = async () => {
  try {
    const response = await axios.get(`${BASE_URL}`);
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};
export const createCategory = async (category) => {
  try {
    const response = await axios.post(`${BASE_URL}`, category);
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};

export const getCategoryById = async (id) => {
  try {
    const response = await axios.get(`${BASE_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};

export const updateCategory = async (id, details) => {
  try {
    const response = await axios.patch(`${BASE_URL}/${id}`, details);
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};

export const deleteCategory = async (id) => {
  try {
    const response = await axios.delete(`${BASE_URL}/${id}`);
    return response.data;
  } catch (error) {
    console.log(error.response);
    throw error;
  }
};
