import React from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { createCountry } from "../../../service/CountryService";

export default function CountryCreateForm({ handleClose }) {
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const formData = new FormData(e.target);
      const payload = Object.fromEntries(formData);

      await createCountry(payload);
      alert("Country created successfully");
      handleClose();
    } catch (e) {
      alert("Failed to create country. Please try again.");
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
