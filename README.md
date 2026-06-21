# Book Management REST API

An elegant, production-ready RESTful API built with **Spring Boot** and **PostgreSQL** to manage a book catalog. This project showcases enterprise-grade patterns, including DTO-based communication, structured logging, centralized error handling, and robust data persistence.

## 🚀 Key Features

* **Layered Architecture:** Clear separation of concerns (Web, Service, Data Access).
* **DTO Pattern:** Secure and optimized data transfer using modern Java `record` types.
* **Data Validation:** Strict inbound payload validation using `jakarta.validation` constraints.
* **Centralized Exception Handling:** Uniform HTTP error responses via a `@RestControllerAdvice`.
* **Database Seeding:** Automated initial data population on startup with safe conditional checks.
* **Comprehensive Logging:** Multi-level structured logs (`TRACE` to `INFO`) written to both console and rolling file.

---

## 🛠️ Tech Stack & Dependencies

* **Java 17 / 21**
* **Spring Boot 3.x**
    * Spring Web
    * Spring Data JPA (Hibernate)
    * Validation
* **PostgreSQL** (Database)

---

## 🚦 Getting Started

### Prerequisites
* JDK 17 or higher
* Maven 3.x
* PostgreSQL instance running locally or via Docker
* Git installed on your machine

### 👥 How to Clone and Run Locally

Segui questi passaggi per clonare il repository e avviare il progetto sul tuo computer:

1. **Clona il repository:**
   Apri il terminale e scrivi il seguente comando:
   ```bash
   git clone https://github.com/Waldren56/Books-REST-API.git
