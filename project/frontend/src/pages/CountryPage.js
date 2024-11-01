import React, { useCallback, useEffect, useState } from "react";
import CountryModal from "../components/modals/CountryModal";
import CountryAccordion from "../components/accordions/CountryAccordion";
import {
  getCountriesWithPagination,
  deleteCountry,
  updateCountry,
} from "../service/CountryService";
import Button from "react-bootstrap/Button";

import CustomPagination from "../components/pagination/CustomPagination";
import PaginationForm from "../components/pagination/PaginationForm";
import { Container, Row, Col } from "react-bootstrap";

export default function CountryPage() {
  const [showModal, setShowModal] = useState(false);
  const [countries, setCountries] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [totalCountries, setTotalCountries] = useState(0);
  const [pageSize, setPageSize] = useState(5);
  const [sortingProperty, setSortingProperty] = useState("name");
  const [sortingOrder, setSortingOrder] = useState("ASC");

  const getAllCountries = useCallback(async () => {
    try {
      const data = await getCountriesWithPagination(
        currentPage,
        pageSize,
        sortingProperty,
        sortingOrder
      );
      setCountries(data.content);
      setTotalPages(data.totalPages);
      setTotalCountries(data.totalElements);
    } catch (e) {
      console.log("Error fetching countries", e);
    }
  }, [currentPage, pageSize, sortingProperty, sortingOrder]);

  useEffect(() => {
    getAllCountries();
  }, [getAllCountries]);

  const removeCountry = async (countryId) => {
    try {
      await deleteCountry(countryId);
      await getAllCountries();
    } catch (error) {
      console.log("Error deleting country:", error);
    }
  };

  const editCountry = async (id, countryDetails) => {
    try {
      await updateCountry(id, countryDetails);
      await getAllCountries();
    } catch (error) {
      console.log("Error updating country:", error);
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
        <h2>Countries</h2>
      </Row>
      <Row className="mb-3">
        <Col xs="auto">
          <Button variant="primary" onClick={(e) => setShowModal(true)}>
            Add
          </Button>{" "}
        </Col>
      </Row>
      <CountryModal show={showModal} handleClose={handleCloseModal} />
      <Row className="mb-3">
        <CountryAccordion
          countries={countries}
          patchFunction={editCountry}
          deleteFunction={removeCountry}
        />
      </Row>
      <Row className="mb-3 ">
        <p>Total Countries: {totalCountries}</p>
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
