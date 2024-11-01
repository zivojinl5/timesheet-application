import React, { useState, useEffect, useCallback } from "react";
import CustomPagination from "../components/pagination/CustomPagination";
import ClientAccordion from "../components/accordions/ClientAccordion";
import ClientModal from "../components/modals/ClientModal";
import PaginationForm from "../components/pagination/PaginationForm";
import Button from "react-bootstrap/Button";

import {
  getAllStartingLetters,
  getClientsWithPagination,
  deleteClient,
  updateClient,
  searchClients,
} from "../service/ClientService";
import { getCountries } from "../service/CountryService";
import { FaSearch } from "react-icons/fa";
import { Container, Row } from "react-bootstrap";

export default function ClientPage() {
  const [showModal, setShowModal] = useState(false);

  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [totalClients, setTotalClients] = useState(0);
  const [pageSize, setPageSize] = useState(5);
  const [sortingProperty, setSortingProperty] = useState("name");
  const [sortingOrder, setSortingOrder] = useState("ASC");

  const [usedLetters, setUsedLetters] = useState([]);
  const [startingLetter, setStartingLetter] = useState("");
  const [searchString, setSearchString] = useState("");

  const [clients, setClients] = useState([]);
  const [countries, setCountries] = useState([]);

  //if there are no search parameters, function will fetch all clients
  const getAllClients = useCallback(async () => {
    try {
      let data;
      if (startingLetter || searchString) {
        data = await searchClients(
          startingLetter,
          currentPage,
          pageSize,
          sortingProperty,
          sortingOrder,
          searchString
        );
      } else {
        data = await getClientsWithPagination(
          currentPage,
          pageSize,
          sortingProperty,
          sortingOrder
        );
      }
      setClients(data.content);
      setTotalPages(data.totalPages);
      setTotalClients(data.totalElements);
    } catch (e) {
      console.log("Error fetching clients:", e);
    }
  }, [
    currentPage,
    pageSize,
    sortingProperty,
    sortingOrder,
    searchString,
    startingLetter,
  ]);

  const fetchCountries = useCallback(async () => {
    try {
      const countriesData = await getCountries();
      setCountries(countriesData);
    } catch (e) {
      console.log("Error fetching countries:", e);
    }
  }, []);

  const fetchUsedStartingLetters = useCallback(async () => {
    try {
      const letters = await getAllStartingLetters();
      setUsedLetters(letters);
      const firstAvailableLetter = letters.find(
        (letter) => !usedLetters.includes(letter)
      );
      if (firstAvailableLetter) {
        setStartingLetter(firstAvailableLetter);
      }
    } catch (e) {
      console.log("Error fetching used starting letters:", e);
    }
  }, [usedLetters]);

  useEffect(() => {
    getAllClients();
    fetchCountries();
    fetchUsedStartingLetters();
  }, [getAllClients, fetchCountries, fetchUsedStartingLetters]);

  const renderLetters = () => {
    const alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    return alphabet.split("").map((letter) => {
      const isUsed = usedLetters.includes(letter);
      return (
        <span
          key={letter}
          className={`letter ${
            startingLetter === letter ? "bg-primary text-white" : ""
          }`}
          onClick={isUsed ? () => handleLetterClick(letter) : null}
          style={{
            margin: "5px",
            cursor: isUsed ? "pointer" : "default",
            opacity: isUsed ? 1 : 0.5,
          }}
        >
          {letter}
        </span>
      );
    });
  };
  const handleLetterClick = (letter) => {
    setStartingLetter(letter);
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

  const handleSearchStringChange = (e) => {
    setSearchString(e.target.value);
  };

  const removeClient = (clientId) => {
    deleteClient(clientId)
      .then(getAllClients)
      .catch((error) => {
        console.log(error);
      });
  };

  const editClient = (id, clientDetails) => {
    updateClient(id, clientDetails)
      .then(getAllClients)
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <Container>
      <Row className="mb-3">
        {" "}
        <h2>Clients</h2>
      </Row>

      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          marginBottom: "10px",
        }}
      >
        <Button variant="primary" onClick={(e) => setShowModal(true)}>
          Add
        </Button>{" "}
        <div style={{ position: "relative" }}>
          <input
            type="text"
            placeholder="Search Term"
            value={searchString}
            onChange={handleSearchStringChange}
            style={{
              padding: "5px",
              paddingRight: "30px",
              borderRadius: "5px",
              border: "1px solid #ccc",
            }}
          />
          <FaSearch
            style={{
              position: "absolute",
              top: "50%",
              right: "10px",
              transform: "translateY(-50%)",
              color: "#999",
            }}
          />{" "}
        </div>
      </div>
      <ClientModal
        show={showModal}
        handleClose={handleCloseModal}
        countries={countries}
      />
      <Row className="mb-3">
        <ClientAccordion
          clients={clients}
          patchFunction={editClient}
          deleteFunction={removeClient}
          countries={countries}
        />
      </Row>
      <div>
        <div
          style={{
            display: "flex",
            justifyContent: "center",
            marginBottom: "10px",
          }}
        >
          {renderLetters()}
        </div>
        <Row className="mb-3 ">
          <p>Total Clients: {totalClients}</p>
        </Row>
        <PaginationForm
          handlePageSizeChange={handlePageSizeChange}
          handleSortingPropertyChange={handleSortingPropertyChange}
          handleSortingOrderChange={handleSortingOrderChange}
          pageSize={pageSize}
          sortingProperty={sortingProperty}
          sortingOrder={sortingOrder}
          sortingProperties={["name", "address", "city", "postalCode"]}
          countries={countries}
        />
      </div>
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
