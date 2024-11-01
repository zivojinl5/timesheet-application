import React from "react";
import { Pagination } from "react-bootstrap";

export default function CustomPagination({
  currentPage,
  totalPages,
  onPageChange,
}) {
  const renderPaginationItems = () => {
    const items = [];
    for (let i = 0; i < totalPages; i++) {
      items.push(
        <Pagination.Item
          key={i + 1}
          active={i + 1 === currentPage}
          onClick={() => onPageChange(i + 1)}
        >
          {i + 1}
        </Pagination.Item>
      );
    }
    return items;
  };

  return (
    <Pagination>
      <Pagination.First onClick={() => onPageChange(1)} />
      <Pagination.Prev onClick={() => onPageChange(currentPage - 1)} />
      {renderPaginationItems()}
      <Pagination.Next onClick={() => onPageChange(currentPage + 1)} />
      <Pagination.Last onClick={() => onPageChange(totalPages)} />
    </Pagination>
  );
}
