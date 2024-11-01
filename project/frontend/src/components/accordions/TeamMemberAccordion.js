import React from "react";
import Accordion from "react-bootstrap/Accordion";
import TeamMemberForm from "../forms/TeamMemberForm";

export default function TeamMemberAccordion({
  teamMembers,
  patchFunction,
  deleteFunction,
}) {
  return (
    <>
      {teamMembers.map((teamMember) => (
        <Accordion key={teamMember.id} defaultActiveKey="0">
          <Accordion.Item eventKey={teamMember.id}>
            <Accordion.Header>{teamMember.name}</Accordion.Header>
            <Accordion.Body>
              <TeamMemberForm
                key={teamMember.id}
                teamMember={teamMember}
                patchFunction={patchFunction}
                deleteFunction={deleteFunction}
              />
            </Accordion.Body>
          </Accordion.Item>
        </Accordion>
      ))}
    </>
  );
}
