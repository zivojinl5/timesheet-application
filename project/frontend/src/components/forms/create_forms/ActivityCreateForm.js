import React, { useState, useEffect } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { createActivity } from "../../../service/ActivityService";
import { getProjectsByClientId } from "../../../service/ProjectService";

import { useParams } from "react-router-dom";
export default function ActivityForm({ clients, categories, handleClose }) {
  const [userId] = useState("1");
  const [selectedClientId, setSelectedClientId] = useState();
  const [selectedProjectId, setSelectedProjectId] = useState();
  const [selectedCategoryId, setSelectedCategoryId] = useState();
  const { fullDate } = useParams();
  const [date] = useState(fullDate);
  const [projects, setProjects] = useState([]);

  useEffect(() => {
    const fetchProjects = async () => {
      if (selectedClientId) {
        try {
          const projectsData = await getProjectsByClientId(selectedClientId);
          setProjects(projectsData);
        } catch (error) {
          console.error("Failed to fetch projects:", error);
        }
      } else {
        setProjects([]);
      }
      setSelectedProjectId(null);
    };

    fetchProjects();
  }, [selectedClientId]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const formData = new FormData(e.target);
      const payload = Object.fromEntries(formData);
      payload.date = date;
      payload.clientId = selectedClientId;
      payload.projectId = selectedProjectId;
      payload.categoryId = selectedCategoryId;
      payload.userId = userId;

      console.log(payload);
      await createActivity(payload);
      alert("Entry added successfully");
      handleClose();
    } catch (e) {
      alert("Failed to create entry. Please try again.");
    }
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group className="mb-3">
        <Form.Label>Description</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter description"
          name="description"
          required
        />
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Hours</Form.Label>
        <Form.Control
          type="number"
          placeholder="Enter hours"
          name="hours"
          required
        />
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Overtime Hours</Form.Label>
        <Form.Control
          type="number"
          placeholder="Enter overtime hours"
          name="overtimeHours"
          required
        />
      </Form.Group>

      <Form.Group className="mb-3">
        <Form.Label>Client</Form.Label>
        <Form.Select
          onChange={(e) => setSelectedClientId(e.target.value)}
          required
        >
          <option>Select client</option>
          {clients.map((client) => (
            <option key={client.id} value={client.id}>
              {client.name}
            </option>
          ))}
        </Form.Select>
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Project</Form.Label>
        <Form.Select
          onChange={(e) => setSelectedProjectId(e.target.value)}
          value={selectedProjectId || ""}
          required
        >
          <option>Select project</option>
          {projects.map((project) => (
            <option key={project.id} value={project.id}>
              {project.name}
            </option>
          ))}
        </Form.Select>
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Category</Form.Label>
        <Form.Select
          onChange={(e) => setSelectedCategoryId(e.target.value)}
          required
        >
          <option>Select category</option>
          {categories.map((category) => (
            <option key={category.id} value={category.id}>
              {category.name}
            </option>
          ))}
        </Form.Select>
      </Form.Group>
      <Button variant="secondary" type="submit">
        Submit
      </Button>
    </Form>
  );
}
