import React from "react";
import Modal from "react-bootstrap/Modal";
import CountryCreateForm from "../forms/create_forms/CountryCreateForm";

export default function CountryModal({ show, handleClose }) {
  return (
    <>
      return (
      <>
        <Modal show={show} onHide={handleClose}>
          <Modal.Header closeButton>
            <Modal.Title>Create new category</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <CountryCreateForm onHide={handleClose}></CountryCreateForm>
          </Modal.Body>
        </Modal>
      </>
      );
    </>
  );
}
