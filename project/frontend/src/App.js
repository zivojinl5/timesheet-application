import React from "react";
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Outlet,
} from "react-router-dom";
import CustomNavbar from "./components/nav/CustomNavbar";
import MonthPage from "./pages/MonthPage";
import TeamMemberPage from "./pages/TeamMemberPage";
import ClientPage from "./pages/ClientPage";
import CategoryPage from "./pages/CategoryPage";
import CountryPage from "./pages/CountryPage";
import DayPage from "./pages/DayPage";
import NoPage from "./pages/NoPage";
import { Container } from "react-bootstrap";

export default function App() {
  return (
    <Container fluid className="bg-light">
      <Router>
        <CustomNavbar />
        <Routes>
          <Route path="/" element={<Outlet />}>
            <Route index element={<MonthPage />} />
            <Route path="/month" element={<MonthPage />} />
            <Route path="/day/:fullDate" element={<DayPage />} />
            <Route path="/team-members" element={<TeamMemberPage />} />
            <Route path="/clients" element={<ClientPage />} />
            <Route path="/categories" element={<CategoryPage />} />
            <Route path="/countries" element={<CountryPage />} />
            <Route path="*" element={<NoPage />} />
          </Route>
        </Routes>
      </Router>
    </Container>
  );
}
