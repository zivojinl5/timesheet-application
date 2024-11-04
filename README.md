# Timesheet Application

This is a timesheet management tool designed to help users track work hours efficiently. With a focus on scalability and user-friendly design, the application features a **Spring Boot** backend and a **React** frontend, allowing for a smooth and responsive experience. Users can log daily work hours, monitor overtime, and manage client information, with database support for reliable data storage.

## Project Structure
- **Backend**: Spring Boot application for managing the server, API, and database interactions.
- **Frontend**: React application for the user interface.

---

## Technologies Used
- **Backend**: Spring Boot, Java, Maven
- **Frontend**: React, JavaScript, Bootstrap, React Bootstrap
- **Database**: MySQL
- **HTTP Client**: Axios

---

## Getting Started

### Prerequisites
- **Java** and **Maven** for the backend
- **Node.js** and **npm** for the frontend
- **MySQL** database

### Setting Up the Application

#### Step 1: Configure Database Connection

1. Go to `timesheet-application/project/backend/src/main/resources/application.properties`.
2. Update the following lines with your MySQL connection details:

    ```properties
    spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/time_sheet_db 
    spring.datasource.username=${MYSQL_USER:your-username}
    spring.datasource.password=${MYSQL_PASSWORD:your-password}
    ```

#### Step 2: Start the Backend Server

1. Open a terminal.
2. Navigate to the backend directory:

    ```bash
    cd timesheet-application/project/backend/
    ```

3. Start the Spring Boot server with the following command:

    ```bash
    mvn spring-boot:run
    ```

#### Step 3: Start the Frontend Server

1. Open a new terminal.
2. Navigate to the frontend directory:

    ```bash
    cd timesheet-application/project/frontend/
    ```

3. Start the React application with:

    ```bash
    npm start
    ```

The application should now be running with the backend on **http://localhost:8080** and the frontend on **http://localhost:3000**.

---

## Main Features

- **Calendar View**: Displays the number of worked hours for each day. Days with overtime are highlighted in orange for easy visibility.
    - **Day Detail View**: Clicking on a day opens a page to enter or edit hours worked, allowing for precise tracking.
- **Client Management**: 
    - Add, edit, and delete clients easily.
    - Sort and filter clients by their starting letter or by keywords to quickly find specific clients.

---

## License
This project is open-source and available for personal or educational use.
 
