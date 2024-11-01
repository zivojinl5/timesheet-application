import React, { useState, useEffect } from "react";
import { Form, Row, Col } from "react-bootstrap";
import Button from "react-bootstrap/Button";

export default function ClientForm({
  client,
  countries,
  patchFunction,
  deleteFunction,
}) {
  const [name, setName] = useState(client.name);
  const [address, setAddress] = useState(client.address);
  const [city, setCity] = useState(client.city);
  const [postalCode, setPostalCode] = useState(client.postalCode);

  const [selectedCountryId, setSelectedCountryId] = useState(client.country.id);

  useEffect(() => {}, [selectedCountryId]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = {
      name,
      address,
      city,
      postalCode,
      countryId: selectedCountryId,
    };
    try {
      patchFunction(client.id, formData);
    } catch (e) {
      console.error("Error updating client:", e);
    }
  };

  return (
    <Form>
      <Row>
        <Col>
          <Form.Group controlId="name">
            <Form.Label>Name</Form.Label>
            <Form.Control
              type="text"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </Form.Group>
        </Col>
        <Col>
          <Form.Group controlId="address">
            <Form.Label>Address</Form.Label>
            <Form.Control
              type="text"
              value={address}
              onChange={(e) => setAddress(e.target.value)}
            />
          </Form.Group>
        </Col>
        <Col>
          <Form.Group controlId="city">
            <Form.Label>City</Form.Label>
            <Form.Control
              type="text"
              value={city}
              onChange={(e) => setCity(e.target.value)}
            />
          </Form.Group>
        </Col>
        <Col>
          <Form.Group controlId="postalCode">
            <Form.Label>Postal Code</Form.Label>
            <Form.Control
              type="text"
              value={postalCode}
              onChange={(e) => setPostalCode(e.target.value)}
            />
          </Form.Group>
        </Col>
      </Row>
      <Row>
        <Col>
          <Form.Group controlId="countrySelect">
            <Form.Label>Country</Form.Label>
            <Form.Control
              as="select"
              value={selectedCountryId}
              onChange={(e) => setSelectedCountryId(e.target.value)}
            >
              {countries.map((country) => (
                <option key={country.id} value={country.id}>
                  {country.name}
                </option>
              ))}
            </Form.Control>
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
          <Button variant="danger" onClick={() => deleteFunction(client.id)}>
            Delete
          </Button>
        </Col>
      </Row>
    </Form>
  );
}
