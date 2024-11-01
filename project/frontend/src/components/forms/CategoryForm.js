import React, { useState } from "react";
import { Form, Row, Col } from "react-bootstrap";
import Button from "react-bootstrap/Button";

export default function CategoryForm({
  category,
  patchFunction,
  deleteFunction,
}) {
  const [name, setName] = useState(category.name);

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
      patchFunction(category.id, filteredData);
    } catch (error) {
      console.error("Error updating category:", error);
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
              placeholder="Enter category name"
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
          <Button variant="danger" onClick={() => deleteFunction(category.id)}>
            Delete
          </Button>
        </Col>
      </Row>
    </Form>
  );
}
