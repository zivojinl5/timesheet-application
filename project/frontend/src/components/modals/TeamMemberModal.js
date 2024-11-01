import React from "react";
import Modal from "react-bootstrap/Modal";
import TeamMemberCreateForm from "../forms/create_forms/TeamMemberCreateForm.tsx";

export default function TeamMemberModal({ show, handleClose }) {
  return (
    <>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Create new team member</Modal.Title>
        </Modal.Header>{" "}
        <Modal.Body>
          <TeamMemberCreateForm
            handleClose={handleClose}
          ></TeamMemberCreateForm>
        </Modal.Body>
      </Modal>
    </>
  );
}
