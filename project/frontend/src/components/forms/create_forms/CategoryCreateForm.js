import React from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { createCategory } from "../../../service/CategotyService";

export default function CategoryCreateForm({ handleClose }) {
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const formData = new FormData(e.target);
      const payload = Object.fromEntries(formData);

      await createCategory(payload);
      alert("Category created successfully");
      handleClose();
    } catch (e) {
      alert("Failed to create category. Please try again.");
    }
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group className="mb-3">
        <Form.Label>Name</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter name"
          name="name"
          required
        />
      </Form.Group>

      <Button variant="secondary" type="submit">
        Submit
      </Button>
    </Form>
  );
}
