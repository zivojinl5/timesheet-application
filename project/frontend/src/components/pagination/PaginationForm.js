import React from "react";
import { Form } from "react-bootstrap";

export default function PaginationForm({
  handlePageSizeChange,
  handleSortingPropertyChange,
  handleSortingOrderChange,
  pageSize,
  sortingProperty,
  sortingOrder,
  sortingProperties,
}) {
  return (
    <>
      <Form.Group className="mb-3" controlId="pageSizeSelect">
        <Form.Label>Items per page:</Form.Label>
        <Form.Control
          as="select"
          onChange={handlePageSizeChange}
          value={pageSize}
        >
          <option value={3}>3</option>
          <option value={5}>5</option>
          <option value={10}>10</option>
        </Form.Control>
      </Form.Group>
      <Form.Group className="mb-3" controlId="sortingPropertySelect">
        <Form.Label>Sort Property:</Form.Label>
        <Form.Control
          as="select"
          onChange={handleSortingPropertyChange}
          value={sortingProperty}
        >
          {sortingProperties.map((property) => (
            <option key={property} value={property}>
              {property}
            </option>
          ))}
        </Form.Control>
      </Form.Group>
      <Form.Group className="mb-3" controlId="sortingOrderSelect">
        <Form.Label>Sort Order:</Form.Label>
        <Form.Control
          as="select"
          onChange={handleSortingOrderChange}
          value={sortingOrder}
        >
          <option value="ASC">Ascending</option>
          <option value="DESC">Descending</option>
        </Form.Control>
      </Form.Group>
    </>
  );
}
