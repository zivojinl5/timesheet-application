import React from "react";
import Accordion from "react-bootstrap/Accordion";
import ClientForm from "../forms/ClientForm";

export default function ClientAccordion({
  clients,
  patchFunction,
  deleteFunction,
  countries,
}) {
  return (
    <>
      {clients.map((client) => (
        <Accordion key={client.id} defaultActiveKey="0">
          <Accordion.Item eventKey={client.id}>
            <Accordion.Header>{client.name}</Accordion.Header>
            <Accordion.Body>
              <ClientForm
                key={client.id}
                client={client}
                patchFunction={patchFunction}
                deleteFunction={deleteFunction}
                countries={countries}
              />
            </Accordion.Body>
          </Accordion.Item>
        </Accordion>
      ))}
    </>
  );
}
