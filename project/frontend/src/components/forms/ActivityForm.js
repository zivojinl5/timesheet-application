import React, { useState, useEffect, useCallback } from "react";
import { Form } from "react-bootstrap";
import Button from "react-bootstrap/Button";
import { useParams } from "react-router-dom";
import { getProjectsByClientId } from "../../service/ProjectService";

export default function ActivityForm({
  activity,
  clients,
  categories,
  patchFunction,
  deleteFunction,
}) {
  const [description, setDescription] = useState(activity.description);
  const [hours, setHours] = useState(activity.hours);
  const [overtimeHours, setOvertimeHours] = useState(activity.overtimeHours);
  const { fullDate } = useParams();
  const [date] = useState(fullDate);
  const [userId] = useState(1);
  const [selectedClientId, setSelectedClientId] = useState(activity.client.id);
  const [selectedProjectId, setSelectedProjectId] = useState(
    activity.project.id
  );
  const [selectedCategoryId, setSelectedCategoryId] = useState(
    activity.category.id
  );
  const [projects, setProjects] = useState([]);

  const fetchProjects = useCallback(async () => {
    if (selectedClientId) {
      try {
        const projectsData = await getProjectsByClientId(selectedClientId);
        setProjects(projectsData);
      } catch (error) {
        console.error("Failed to fetch projects:", error);
      }
    } else {
      setProjects([]); // Clear projects if no client is selected
    }
  }, [selectedClientId]);

  useEffect(() => {
    fetchProjects();
  }, [fetchProjects]);
  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = {
      date,
      description,
      hours,
      overtimeHours,
      clientId: selectedClientId,
      projectId: selectedProjectId,
      categoryId: selectedCategoryId,
      userId: userId,
    };
    console.log("Form Data to be submitted:", formData);

    try {
      await patchFunction(activity.id, formData);
    } catch (e) {
      console.error("Error updating activity:", e);
    }
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Form.Group className="mb-3">
        <Form.Label>Description</Form.Label>
        <Form.Control
          type="text"
          placeholder="Enter description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Hours</Form.Label>
        <Form.Control
          type="number"
          value={hours}
          onChange={(e) => setHours(e.target.value)}
        />
      </Form.Group>
      <Form.Group className="mb-3">
        <Form.Label>Overtime Hours</Form.Label>
        <Form.Control
          type="number"
          value={overtimeHours}
          onChange={(e) => setOvertimeHours(e.target.value)}
        />
      </Form.Group>

      <Form.Group className="mb-3">
        <Form.Label>Client</Form.Label>
        <Form.Select
          value={selectedClientId}
          onChange={(e) => {
            setSelectedClientId(e.target.value);
            setSelectedProjectId(null);
          }}
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
          value={selectedProjectId || ""}
          onChange={(e) => setSelectedProjectId(e.target.value)}
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
          value={selectedCategoryId}
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

      <Form.Group className="mb-3">
        <Button variant="secondary" type="submit">
          Edit
        </Button>
      </Form.Group>
      <Form.Group className="mb-3">
        <Button variant="danger" onClick={() => deleteFunction(activity.id)}>
          Delete
        </Button>
      </Form.Group>
    </Form>
  );
}
