import React from "react";
import Accordion from "react-bootstrap/Accordion";
import CountryForm from "../forms/CountryForm";

export default function CountryAccordion({
  countries,
  patchFunction,
  deleteFunction,
}) {
  return (
    <>
      {countries.map((country) => (
        <Accordion key={country.id} defaultActiveKey="0">
          <Accordion.Item eventKey={country.id}>
            <Accordion.Header>{country.name}</Accordion.Header>
            <Accordion.Body>
              <CountryForm
                key={country.id}
                country={country}
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
