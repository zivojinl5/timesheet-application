import React, { useCallback, useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import {
  deleteActivity,
  getActivitiesByDateAndUserId,
  updateActivity,
} from "../service/ActivityService";
import { getCategories } from "../service/CategotyService";
import { getClients } from "../service/ClientService";
import { Container, Row, Col } from "react-bootstrap";
import ActivityAccordion from "../components/accordions/ActivityAccordion";
import ActiviyModal from "../components/modals/ActivityModal";
import Button from "react-bootstrap/Button";

export default function DayPage() {
  const [showModal, setShowModal] = useState(false);

  const { fullDate } = useParams();
  const [date] = useState(fullDate);
  const [totalHours, setTotalHours] = useState(0);

  const [userId] = useState(1);

  const [activities, setActivities] = useState([]);
  const [clients, setClients] = useState([]);
  const [categories, setCategories] = useState([]);

  const getAllActivities = useCallback(async () => {
    try {
      const data = await getActivitiesByDateAndUserId(date, userId);
      if (data && data.activities) {
        setActivities(data.activities);
        setTotalHours(data.totalHours);
      } else {
        setActivities([]);
        setTotalHours(0);
        console.log("No activities found in the response.");
      }
    } catch (error) {
      console.error("Error fetching activities:", error);
    }
  }, [date, userId]);

  const fetchClients = useCallback(async () => {
    try {
      const clientsData = await getClients();
      setClients(clientsData);
    } catch (error) {
      console.error("Error fetching clients:", error);
    }
  }, []);
  const fetchCategories = useCallback(async () => {
    try {
      const categoriesData = await getCategories();
      setCategories(categoriesData);
    } catch (error) {
      console.error("Error fetching categories:", error);
    }
  }, []);

  useEffect(() => {
    getAllActivities();
    fetchClients();
    fetchCategories();
  }, [getAllActivities, fetchClients, fetchCategories]);

  const removeActivity = (activityId) => {
    deleteActivity(activityId)
      .then(getAllActivities)
      .catch((error) => {
        console.log(error);
      });
  };

  const editActivity = (id, activityDetails) => {
    updateActivity(id, activityDetails)
      .then(getAllActivities)
      .catch((error) => {
        console.log(error);
      });
  };
  const handleCloseModal = () => {
    setShowModal(false);
  };

  return (
    <Container>
      <Row>
        <h2>Activities</h2>
      </Row>
      <Row className="mb-3">
        <Col xs="auto">
          <Button variant="primary" onClick={(e) => setShowModal(true)}>
            Add
          </Button>{" "}
        </Col>
      </Row>
      <ActiviyModal
        show={showModal}
        handleClose={handleCloseModal}
        clients={clients}
        categories={categories}
      />
      <Row className="mb-3">
        <ActivityAccordion
          activities={activities}
          patchFunction={editActivity}
          deleteFunction={removeActivity}
          clients={clients}
          categories={categories}
        />
      </Row>
      <Row>Total Activities: {activities.length}</Row>
      <Row>Total Hours: {totalHours}</Row>
    </Container>
  );
}
