import React from "react";
import Modal from "react-bootstrap/Modal";
import CategoryCreateForm from "../forms/create_forms/CategoryCreateForm";

export default function CategoryModal({ show, handleClose }) {
  return (
    <>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Create new category</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <CategoryCreateForm handleClose={handleClose}></CategoryCreateForm>
        </Modal.Body>
      </Modal>
    </>
  );
}
