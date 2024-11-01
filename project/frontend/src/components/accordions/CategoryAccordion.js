import React from "react";
import Accordion from "react-bootstrap/Accordion";
import CategoryForm from "../forms/CategoryForm";

export default function CategoryAccordion({
  categories,
  patchFunction,
  deleteFunction,
}) {
  return (
    <>
      {categories.map((category) => (
        <Accordion key={category.id} defaultActiveKey="0">
          <Accordion.Item eventKey={category.id}>
            <Accordion.Header>{category.name}</Accordion.Header>
            <Accordion.Body>
              <CategoryForm
                key={category.id}
                category={category}
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
