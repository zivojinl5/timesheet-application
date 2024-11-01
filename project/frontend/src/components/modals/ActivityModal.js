import React from "react";
import Modal from "react-bootstrap/Modal";
import ActivityCreateForm from "../forms/create_forms/ActivityCreateForm";

export default function ActiviyModal({
  show,
  handleClose,
  clients,
  projects,
  categories,
}) {
  return (
    <>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add new entry</Modal.Title>
        </Modal.Header>{" "}
        <Modal.Body>
          <ActivityCreateForm
            handleClose={handleClose}
            clients={clients}
            projects={projects}
            categories={categories}
          ></ActivityCreateForm>
        </Modal.Body>
      </Modal>
    </>
  );
}
