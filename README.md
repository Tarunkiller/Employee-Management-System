# 🚀 Employee Management System

A full-featured CRUD REST API built using **Spring Boot**, **Spring Data JPA**, and **H2 Database** for managing employee records.

---

## 📌 Project Overview

This project is a backend application that provides RESTful APIs to:

- Create an Employee
- Read Employee details
- Update Employee information
- Delete Employee records

The application follows a clean layered architecture:

Controller → Service → Repository → Database

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database (In-Memory)
- Maven
- Lombok
- Postman (for testing)

---


## 📂 Project Structure

src/main/java/com/example/employeemanagement
│
├── EmployeeManagementApplication.java
│
├── controller
│   └── EmployeeController.java
│
├── service
│   ├── EmployeeService.java
│   └── EmployeeServiceImpl.java
│
├── repository
│   └── EmployeeRepository.java
│
├── entity
│   └── Employee.java
│
└── exception
    ├── ResourceNotFoundException.java
    └── GlobalExceptionHandler.java

src/main/resources
│
├── application.properties
└── employees.json

---

## 🗃️ Employee Entity Fields

| Field | Type | Description |
|--------|--------|-------------|
| id | Long | Auto-generated Primary Key |
| fullName | String | Required |
| email | String | Required & Unique |
| phone | String | Required |
| department | String | Required |
| role | String | Required |
| salary | Double | Required |
| dateOfJoining | LocalDate | Required |
| createdAt | LocalDateTime | Auto-generated |
| updatedAt | LocalDateTime | Auto-updated |

---

## ⚙️ How to Run the Project

### 1️⃣ Clone the Repository

```bash
git clone <your-repo-url>
cd springboost
2️⃣ Run Using Maven
mvn spring-boot:run

OR (using wrapper)

./mvnw spring-boot:run
3️⃣ Access the Application

Base URL:

http://localhost:8080/api/employees
🔥 API Endpoints
➤ Create Employee
POST /api/employees
➤ Get All Employees
GET /api/employees
➤ Get Employee by ID
GET /api/employees/{id}
➤ Update Employee
PUT /api/employees/{id}
➤ Delete Employee
DELETE /api/employees/{id}
📬 Sample JSON (Create / Update)
{
  "fullName": "Tarun",
  "email": "tarun@example.com",
  "phone": "1234567899",
  "department": "Engineering",
  "role": "Software Engineer",
  "salary": 75000.0,
  "dateOfJoining": "2024-05-01"
}
❗ Exception Handling

404 → Employee Not Found

400 → Validation Errors

500 → Internal Server Error

Handled globally using @RestControllerAdvice.

🧪 Testing

Tested using:

Postman

Browser (GET requests)

📌 Database

Currently using:

H2 In-Memory Database

The database resets every time the application restarts.

🚀 Future Enhancements

Add Swagger UI

Add Pagination & Sorting

Switch to MySQL

Add DTO layer

Add Authentication (JWT)

Add Unit Testing

👨‍💻 Author

Developed as part of backend learning using Spring Boot.
