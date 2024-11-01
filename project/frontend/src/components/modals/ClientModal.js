import React from "react";
import Modal from "react-bootstrap/Modal";

import ClientCreateForm from "../forms/create_forms/ClientCreateForm";

export default function ClientModal({ show, handleClose, countries }) {
  return (
    <>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Create new client</Modal.Title>
        </Modal.Header>{" "}
        <Modal.Body>
          <ClientCreateForm
            handleClose={handleClose}
            countries={countries}
          ></ClientCreateForm>
        </Modal.Body>
      </Modal>
    </>
  );
}
