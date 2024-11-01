import React, { useCallback, useEffect, useState } from "react";
import TeamMemberModal from "../components/modals/TeamMemberModal";
import TeamMemberAccordion from "../components/accordions/TeamMemberAccordion";
import {
  getTeamMembers,
  deleteTeamMember,
  updateTeamMember,
} from "../service/TeamMemberService";
import Button from "react-bootstrap/Button";

import CustomPagination from "../components/pagination/CustomPagination";
import PaginationForm from "../components/pagination/PaginationForm";
import { Container, Row, Col } from "react-bootstrap";

export default function TeamMemberPage() {
  const [showModal, setShowModal] = useState(false);
  const [teamMembers, setTeamMembers] = useState([]);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [totalTeamMembers, setTotalTeamMembers] = useState(0);
  const [pageSize, setPageSize] = useState(5);
  const [sortingProperty, setSortingProperty] = useState("name");
  const [sortingOrder, setSortingOrder] = useState("ASC");

  const getAllTeamMembers = useCallback(async () => {
    try {
      const data = await getTeamMembers(
        currentPage,
        pageSize,
        sortingProperty,
        sortingOrder
      );
      setTeamMembers(data.content);
      setTotalPages(data.totalPages);
      setTotalTeamMembers(data.totalElements);
    } catch (e) {
      console.log("Error fetching team members", e);
    }
  }, [currentPage, pageSize, sortingProperty, sortingOrder]);

  useEffect(() => {
    getAllTeamMembers();
  }, [getAllTeamMembers]);

  const removeTeamMember = async (teamMemberId) => {
    try {
      await deleteTeamMember(teamMemberId);
      await getAllTeamMembers();
    } catch (error) {
      console.log("Error deleting team member:", error);
    }
  };

  const editTeamMember = async (id, teamMemberDetails) => {
    try {
      await updateTeamMember(id, teamMemberDetails);
      await getAllTeamMembers();
    } catch (error) {
      console.log("Error updating team member:", error);
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
        <h2>Team Members</h2>
      </Row>

      <Row className="mb-3">
        <Col xs="auto">
          <Button variant="primary" onClick={(e) => setShowModal(true)}>
            Add
          </Button>{" "}
        </Col>
      </Row>

      <TeamMemberModal show={showModal} handleClose={handleCloseModal} />
      <Row className="mb-3">
        <TeamMemberAccordion
          teamMembers={teamMembers}
          patchFunction={editTeamMember}
          deleteFunction={removeTeamMember}
        />
      </Row>
      <Row className="mb-3 ">
        <p>Total Team Members: {totalTeamMembers}</p>
      </Row>
      <Row className="mb-3">
        <PaginationForm
          handlePageSizeChange={handlePageSizeChange}
          handleSortingPropertyChange={handleSortingPropertyChange}
          handleSortingOrderChange={handleSortingOrderChange}
          pageSize={pageSize}
          sortingProperty={sortingProperty}
          sortingOrder={sortingOrder}
          sortingProperties={["name", "username", "hours"]}
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
