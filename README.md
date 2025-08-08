# Payment API

[![Build](https://img.shields.io/badge/build-passing-brightgreen)](#)
[![Java](https://img.shields.io/badge/Java-21-blue)](#)
[![License](https://img.shields.io/badge/license-MIT-yellow)](#)

A Spring Boot REST API for managing payments. It exposes endpoints to create, read, and delete payment records and includes health checks, validation, and database integration.

## Features

- Java 21 & Spring Boot 3.5
- REST endpoints for payment CRUD
- Spring Data JPA with H2 (dev) and MySQL (prod) profiles
- Jakarta Bean Validation for request validation
- Global exception handling
- OpenAPI/Swagger documentation
- Unit and integration tests with JUnit 5, Mockito, and MockMvc

## Folder Structure

```plaintext
├── pom.xml                               # Maven configuration and dependencies
└── src
    ├── main
    │   ├── java/com/antoniotmdev/app_interview
    │   │   ├── AppInterviewApplication.java      # Application entry point
    │   │   ├── controller/                       # REST controllers
    │   │   ├── dto/                              # Request/response DTOs
    │   │   ├── entity/                           # JPA entities
    │   │   ├── repository/                       # Repositories (Spring Data JPA)
    │   │   ├── service/                          # Business logic
    │   │   └── exception/                        # Custom exceptions & handlers
    │   └── resources
    │       ├── application.properties            # Active profile selector
    │       ├── application-dev.properties        # Dev profile (H2, debug logging)
    │       ├── application-prod.properties       # Prod profile (MySQL example)
    │       └── data.sql                          # Initial data for dev
    └── test
        ├── AppInterviewApplicationTests.java     # Context load test
        ├── unit/service/PaymentServiceTest.java  # Unit tests
        └── integration/controller/PaymentControllerIntegrationTest.java
                                                  # Integration tests
```

## Getting Started:

1. **Clone the repository**
   ```bash
   git clone https://github.com/antoniotmdev/payment-api.git
   cd payment-api
   
2. **Check requirements**

- Java 21 installed and JAVA_HOME configured.

- Maven 3.9+ installed (or use the included mvnw wrapper).

3. **Install dependencies**
   ```bash
   mvn clean install
   
4. **Run in development mode (H2 in-memory database)**
   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
   
5. **Access the API**
   
   - http://localhost:8080/v1/api/swagger-ui/index.html
   - http://localhost:8080/v1/api/v3/api-docs
     
7. **Stop the application**
   
- Press CTRL + C in the terminal where the app is running.

## Endpoints

| Method | Path               | Description                | Request Body           | Success Responses         |
|-------:|--------------------|----------------------------|------------------------|---------------------------|
| GET    | `/health`          | Simple health check         | —                      | `200 OK`                  |
| GET    | `/payments`        | Retrieve all payments       | —                      | `200 OK (Payment[])`      |
| GET    | `/payments/{id}`   | Retrieve payment by ID      | —                      | `200 OK (Payment)`        |
| POST   | `/payments`        | Create a new payment        | `PaymentRequestDto`    | `201 Created (Payment)`   |
| DELETE | `/payments/{id}`   | Delete payment by ID        | —                      | `204 No Content`          |

> Replace `{id}` with a valid `Long` value.

## Models

**PaymentRequestDto**
```json
{
  "amount": 150.75,
  "currency": "EUR"
}
```

**PaymentEntity (response)**
```json
{
  "id": 1,
  "amount": 150.75,
  "currency": "EUR",
  "timestamp": "2025-08-08T16:10:23.412345"
}
```

---

## Example Requests (cURL)

> Adjust base URL if modify `server.servlet.context-path=/v1/api`.

**Health Check**
```bash
curl -i http://localhost:8080/v1/api/health
```

**Create Payment**
```bash
curl -i -X POST http://localhost:8080/v1/api/payments \
  -H "Content-Type: application/json" \
  -d '{
        "amount": 150.75,
        "currency": "EUR"
      }'
```

**List Payments**
```bash
curl -s http://localhost:8080/v1/api/payments
```

**Get by ID**
```bash
curl -i http://localhost:8080/v1/api/payments/1
```

**Delete by ID**
```bash
curl -i -X DELETE http://localhost:8080/v1/api/payments/1
```

---

## Error Responses

**404 – Payment not found**
```json
{
  "error": "Payment not found",
  "path": "/payments/999",
  "status": 404,
  "timestamp": "2025-08-08T16:18:51.815Z"
}
```

**400 – Validation error**
```json
{
  "error": "Validation failed",
  "status": 400,
  "timestamp": "2025-08-08T16:20:12.001Z",
  "violations": [
    { "field": "amount",   "message": "must be greater than 0" },
    { "field": "currency", "message": "size must be 3" }
  ]
}
```
