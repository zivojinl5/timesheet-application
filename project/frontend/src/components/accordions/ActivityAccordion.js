import React from "react";
import Accordion from "react-bootstrap/Accordion";
import ActivityForm from "../forms/ActivityForm";

export default function ActivityAccordion({
  activities,
  patchFunction,
  deleteFunction,
  clients,
  categories,
}) {
  return (
    <>
      {activities.map((activity) => (
        <Accordion key={activity.id} defaultActiveKey="0">
          <Accordion.Item eventKey={activity.id}>
            <Accordion.Header>Activity id: {activity.id}</Accordion.Header>
            <Accordion.Body>
              <ActivityForm
                key={activity.id}
                activity={activity}
                patchFunction={patchFunction}
                deleteFunction={deleteFunction}
                clients={clients}
                categories={categories}
              />
            </Accordion.Body>
          </Accordion.Item>
        </Accordion>
      ))}
    </>
  );
}
