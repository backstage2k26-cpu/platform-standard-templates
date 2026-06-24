# Architecture

## 🧱 High-Level Overview
This Java 25 service follows a simple layered architecture:


## 📦 Components
- **Controller Layer**

  - Handles HTTP requests
  - Exposes REST APIs
- **Service Layer**

  - Business logic
- **Domain Layer**
  - Core application logic

## 🐳 Containerization

The service is packaged using Docker.

- Base image: `eclipse-temurin:25`
- Application runs as a standalone JAR

## 🔐 Security (Optional)

- Can be extended with OAuth / JWT
- Secrets managed via environment variables

---

This architecture is intentionally simple and can be extended as needed.
