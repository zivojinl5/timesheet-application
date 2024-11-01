import React from "react";
import { Navbar, Nav } from "react-bootstrap";
import { useLocation } from "react-router-dom";
import logo from "../../assets/images/logo.png";

export default function CustomNavbar() {
  const location = useLocation();
  const activeKey = location.pathname;

  return (
    <Navbar bg="light" expand="lg">
      <Navbar.Brand href="/">
        <img src={logo} alt="Logo" />
      </Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav variant="tabs" activeKey={activeKey}>
          <Nav.Item className="border border-1 border-dark">
            <Nav.Link href="/" eventKey="/">
              TimeSheet
            </Nav.Link>
          </Nav.Item>
          <Nav.Item className="border border-1 border-dark">
            <Nav.Link href="/team-members" eventKey="/team-members">
              Team Members
            </Nav.Link>
          </Nav.Item>
          <Nav.Item className="border border-1 border-dark">
            <Nav.Link href="/clients" eventKey="/clients">
              Clients
            </Nav.Link>
          </Nav.Item>
          <Nav.Item className="border border-1 border-dark">
            <Nav.Link href="/categories" eventKey="/categories">
              Categories
            </Nav.Link>
          </Nav.Item>
          <Nav.Item className="border border-1 border-dark">
            <Nav.Link href="/countries" eventKey="/countries">
              Countries
            </Nav.Link>
          </Nav.Item>
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
}
