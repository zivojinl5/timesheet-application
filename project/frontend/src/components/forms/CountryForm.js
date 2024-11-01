import React, { useState } from "react";
import { Form, Row, Col } from "react-bootstrap";
import Button from "react-bootstrap/Button";

export default function CountryForm(country, patchFunction, deleteFunction) {
  const [name, setName] = useState(country.name);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = {
      name,
    };
    const filteredData = {};
    for (const key in formData) {
      if (formData[key] !== "") {
        filteredData[key] = formData[key];
      }
    }
    try {
      patchFunction(country.id, filteredData);
    } catch (error) {
      console.error("Error updating country:", error);
    }
  };

  return (
    <Form>
      <Row>
        <Col>
          <Form.Group controlId="formName">
            <Form.Label>Name</Form.Label>
            <Form.Control
              type="text"
              placeholder="Enter country name"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </Form.Group>
        </Col>
      </Row>
      <Row className="justify-content-end">
        <Col xs="auto">
          <Button variant="secondary" onClick={handleSubmit}>
            Edit
          </Button>
        </Col>
        <Col xs="auto">
          <Button variant="danger" onClick={() => deleteFunction(country.id)}>
            Delete
          </Button>
        </Col>
      </Row>
    </Form>
  );
}
