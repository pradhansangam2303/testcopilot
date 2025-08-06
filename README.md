# HelloWorld CRUD Application (Spring Boot + Oracle + Thymeleaf)

## Table of Contents
- [Business Requirement Document (BRD)](#business-requirement-document-brd)
- [Application Architecture & Flow](#application-architecture--flow)
- [Technology Stack](#technology-stack)
- [Setup & Run](#setup--run)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Screenshots](#screenshots)
- [Contributing](#contributing)

---

## Business Requirement Document (BRD)

### Objective
Build a simple web application to perform CRUD operations (Create, Read, Update, Delete) on a `Person` entity, using Oracle as the database and Spring Boot for backend logic, with Thymeleaf for the frontend views.

### Requirements
- Users can create, view, edit, and delete Person records.
- Each Person has: `id`, `name`, `email`, `mobile`.
- Data is stored in an Oracle database.
- Web interface for user interaction.

### Functional Flow
1. **Homepage:** Lists all Person records, with options to add, edit, or delete.
2. **Add Person:** Form to enter new details and save to database.
3. **Edit Person:** Form pre-filled with existing data for updates.
4. **Delete Person:** One-click action to remove a record.

---

## Application Architecture & Flow

### Layered Architecture

- **Controller Layer:** Handles HTTP requests and routes them to appropriate services. (e.g., `PersonController`)
- **Service Layer:** Contains business logic and interacts with the repository.
- **Repository Layer:** Connects to Oracle DB using Spring Data JPA (`PersonRepository`).
- **View Layer:** Uses Thymeleaf templates for HTML rendering.

### Code Flow

1. **User opens the web app:** Browser sends HTTP request to the server.
2. **Controller (`PersonController`) processes request:**  
   - For `/` (GET): Fetches all persons and sends to view.
   - For `/add` (GET): Shows empty form for new person.
   - For `/add` (POST): Saves new person via service, redirects to homepage.
   - For `/edit/{id}` (GET): Loads person by id, shows edit form.
   - For `/edit/{id}` (POST): Updates person, redirects to homepage.
   - For `/delete/{id}` (GET): Deletes person, redirects to homepage.
3. **Service (`PersonService`)**  
   - Invokes repository methods for CRUD operations.
4. **Repository (`PersonRepository`)**  
   - Extends `JpaRepository`, auto-implements DB operations.
5. **View Templates (`.html` files)**  
   - Rendered by Thymeleaf, showing forms and lists.

#### Sequence Diagram

```
User  ---> Controller ---> Service ---> Repository ---> Oracle DB
  ^             |             |             |             |
  |-------------|-------------|-------------|-------------|
  <---------- Thymeleaf View <--------------|
```

---

## Technology Stack

- **Java 11+**
- **Spring Boot 2.x**
- **Spring Data JPA**
- **Oracle Database**
- **Thymeleaf**
- **Maven**

---

## Setup & Run

1. **Clone Repository:**  
   `git clone https://github.com/pradhansangam2303/testcopilot.git`

2. **Configure Oracle DB:**  
   Update `application.properties` with your Oracle DB credentials.

3. **Build & Run:**  
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **Access App:**  
   Visit `http://localhost:8080`

---

## Project Structure

```
src/
 └── main/
     ├── java/com/example/helloworld/
     │    ├── controller/PersonController.java
     │    ├── model/Person.java
     │    ├── repository/PersonRepository.java
     │    └── service/PersonService.java
     └── resources/
          ├── templates/
          │    ├── index.html
          │    ├── add.html
          │    └── edit.html
          └── application.properties
pom.xml
README.md
```

---

## API Endpoints

| Method | Endpoint         | Description          |
|--------|------------------|---------------------|
| GET    | `/`              | List all persons    |
| GET    | `/add`           | Show add form       |
| POST   | `/add`           | Save new person     |
| GET    | `/edit/{id}`     | Show edit form      |
| POST   | `/edit/{id}`     | Update person       |
| GET    | `/delete/{id}`   | Delete person       |

---

## Screenshots

*Add screenshots here after running the application.*

---

## Contributing

Feel free to fork, improve, and submit PRs. For issues, open a ticket on GitHub.

````I'm waiting for your confirmation to proceed with pushing the README.md file to your repository.
