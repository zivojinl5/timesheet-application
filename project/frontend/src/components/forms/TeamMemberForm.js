import React, { useState } from "react";
import { Form, Row, Col } from "react-bootstrap";
import Button from "react-bootstrap/Button";

export default function TeamMemberForm({
  teamMember,
  patchFunction,
  deleteFunction,
}) {
  const [name, setName] = useState(teamMember.name);
  const [username, setUsername] = useState(teamMember.username);
  const [role, setRole] = useState(teamMember.role);
  const [hours, setHours] = useState(teamMember.hours);
  const [email, setEmail] = useState(teamMember.email);
  const [userStatus, setUserStatus] = useState(teamMember.userStatus);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = {
      name,
      username,
      role,
      hours,
      email,
      userStatus,
    };
    const filteredData = {};
    for (const key in formData) {
      if (formData[key] !== "") {
        filteredData[key] = formData[key];
      }
    }
    try {
      patchFunction(teamMember.id, filteredData);
    } catch (error) {
      console.error("Error updating team member:", error);
    }
  };

  return (
    <Form>
      <Row>
        <Col>
          <Form.Label>Name</Form.Label>
          <Form.Control
            type="text"
            defaultValue={name}
            onChange={(e) => setName(e.target.value)}
          />
        </Col>
        <Col>
          <Form.Label>Username</Form.Label>
          <Form.Control
            type="text"
            defaultValue={username}
            onChange={(e) => setUsername(e.target.value)}
          />
        </Col>
        <Col>
          <Form.Group className="mb-3">
            <Form.Label>Role</Form.Label>
            <Form.Check
              name={role}
              type="radio"
              label={"Admin"}
              checked={role === "ADMIN"}
              onChange={(e) => setRole("ADMIN")}
            />
            <Form.Check
              name={role}
              type="radio"
              label={"Worker"}
              checked={role === "WORKER"}
              onChange={(e) => setRole("WORKER")}
            />
          </Form.Group>
        </Col>
      </Row>
      <Row>
        <Col>
          <Form.Label>Hours per week</Form.Label>
          <Form.Control
            type="number"
            defaultValue={hours}
            onChange={(e) => setHours(e.target.value)}
          />
        </Col>
        <Col>
          <Form.Label>Email</Form.Label>
          <Form.Control
            type="email"
            defaultValue={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </Col>
        <Col>
          <Form.Group className="mb-3">
            <Form.Label>User Status</Form.Label>
            <Form.Check
              name={role}
              type="radio"
              label={"Active"}
              checked={userStatus === "ACTIVE"}
              onChange={(e) => setUserStatus("ACTIVE")}
            />
            <Form.Check
              name={role}
              type="radio"
              label={"Inactive"}
              checked={userStatus === "INACTIVE"}
              onChange={(e) => setUserStatus("INACTIVE")}
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
          <Button
            variant="danger"
            onClick={() => deleteFunction(teamMember.id)}
          >
            Delete
          </Button>
        </Col>
      </Row>
    </Form>
  );
}
