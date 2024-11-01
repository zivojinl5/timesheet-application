import React, { useCallback, useEffect, useState } from "react";
import CategoryModal from "../components/modals/CategoryModal";
import CategoryAccordion from "../components/accordions/CategoryAccordion";
import {
  getCategoriesWithPagination,
  deleteCategory,
  updateCategory,
} from "../service/CategotyService";
import CustomPagination from "../components/pagination/CustomPagination";
import PaginationForm from "../components/pagination/PaginationForm";
import { Container, Row, Col } from "react-bootstrap";
import Button from "react-bootstrap/Button";

export default function CategoryPage() {
  const [showModal, setShowModal] = useState(false);
  const [categories, setCategories] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [totalCategories, setTotalCategories] = useState(0);
  const [pageSize, setPageSize] = useState(5);
  const [sortingProperty, setSortingProperty] = useState("name");
  const [sortingOrder, setSortingOrder] = useState("ASC");

  const getAllCategories = useCallback(async () => {
    try {
      const data = await getCategoriesWithPagination(
        currentPage,
        pageSize,
        sortingProperty,
        sortingOrder
      );
      setCategories(data.content);
      setTotalPages(data.totalPages);
      setTotalCategories(data.totalElements);
    } catch (error) {
      console.log("Error fetching categories:", error);
    }
  }, [currentPage, pageSize, sortingProperty, sortingOrder]);

  useEffect(() => {
    getAllCategories();
  }, [getAllCategories]);

  const removeCategory = async (categoryId) => {
    try {
      await deleteCategory(categoryId);
      await getAllCategories();
    } catch (error) {
      console.log("Error deleting category:", error);
    }
  };

  const editCategory = async (id, categoryDetails) => {
    try {
      await updateCategory(id, categoryDetails);
      await getAllCategories();
    } catch (error) {
      console.log("Error updating category:", error);
    }
  };

  const handlePageChange = (pageNumber) => {
    setCurrentPage(pageNumber - 1);
  };

  const handlePageSizeChange = (e) => {
    setPageSize(parseInt(e.target.value));
    setCurrentPage(0);
  };

  const handleSortingPropertyChange = (e) => {
    setSortingProperty(e.target.value);
  };

  const handleSortingOrderChange = (e) => {
    setSortingOrder(e.target.value);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  return (
    <Container>
      <Row className="mb-3">
        {" "}
        <h2>Categories</h2>
      </Row>
      <Row className="mb-3">
        <Col xs="auto">
          <Button variant="primary" onClick={(e) => setShowModal(true)}>
            Add
          </Button>
        </Col>
      </Row>
      <CategoryModal show={showModal} handleClose={handleCloseModal} />
      <Row className="mb-3">
        <CategoryAccordion
          categories={categories}
          patchFunction={editCategory}
          deleteFunction={removeCategory}
        />
      </Row>
      <Row className="mb-3 ">
        <p>Total Categories: {totalCategories}</p>
      </Row>
      <Row className="mb-3">
        <PaginationForm
          handlePageSizeChange={handlePageSizeChange}
          handleSortingPropertyChange={handleSortingPropertyChange}
          handleSortingOrderChange={handleSortingOrderChange}
          pageSize={pageSize}
          sortingProperty={sortingProperty}
          sortingOrder={sortingOrder}
          sortingProperties={["name"]}
        />
      </Row>

      <Row className="mb-3">
        <CustomPagination
          currentPage={currentPage + 1}
          totalPages={totalPages}
          onPageChange={handlePageChange}
        />
      </Row>
    </Container>
  );
}
