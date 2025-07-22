<h1 align="center">
  🎭 Theater Booking API
</h1>

<p align="center">
  A modern, **JWT‑secured** REST API for managing theaters, seats, events & bookings – built with Spring Boot 3, PostgreSQL & Spring Data JPA.  
  <br/>
  <a href="http://localhost:8080/swagger-ui.html"><b>Try it on Swagger UI →</b></a>
</p>

<div align="center">

[![Java](https://img.shields.io/badge/Java-17-blue.svg?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring Boot-3.3.x-brightgreen.svg?logo=spring)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16.x-blue.svg?logo=postgresql)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/license-MIT-blue)](#license)
[![Code Style](https://img.shields.io/badge/Code%20Style-Prettier-ff69b4?logo=prettier)](https://prettier.io/)

</div>

---

## ✨ Key Features

| Area             | Highlights |
|------------------|------------|
| **Authentication** | • BCrypt‑hashed passwords<br/>• Stateless JWT tokens<br/>• Role‑based access (`Admin`, `User`) |
| **Domain**         | • Theaters → Sections → Rows → Seats<br/>• Events & event‑specific seat maps<br/>• Bookings & payments proof‑of‑concept |
| **API UX**         | • Fully documented with **spring‑doc‑openapi**<br/>• Interactive Swagger UI with “Authorize 🔒” button |
| **Data Layer**     | • Spring Data JPA & Hibernate 6<br/>• PostgreSQL with Flyway‑ready structure |
| **Testing**        | • H2 in‑memory profile<br/>• Repository & service unit tests (JUnit 5 + AssertJ) |
| **Dev Experience** | • Lombok for boiler‑plate‑free entities<br/>• Hot reload with Spring DevTools |

---

## 🏗️ Project Structure

```text
.
├── src/main/java/com/example/theaterbooking
│   ├── auth/          – JWT filter, DTOs, login/registration
│   ├── config/        – DB + Swagger/OpenAPI config
│   ├── controller/    – REST endpoints
│   ├── model/         – JPA entities (Theater, Section, Row, Seat, …)
│   ├── repository/    – Spring Data interfaces
│   ├── service/       – Business logic
│   └── TheaterBookingApplication.java
└── src/main/resources
    ├── application.yml
    └── static/…       – (optional) frontend resources
