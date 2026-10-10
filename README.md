<div align="center">

# 📝 Blog Platform RESTful API Backend
### Production-Grade Enterprise Backend with Spring Boot & JWT Authentication

[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)](https://spring.io/projects/spring-security)
[![JWT](https://img.shields.io/badge/JWT-JSON%20Web%20Tokens-black?style=for-the-badge&logo=jsonwebtokens)](https://jwt.io/)
[![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)

<p align="center">
  <b>A secure, scalable RESTful API engine powering modern blogging platforms with stateless authentication, role-based authorization, and optimized relational data mapping.</b>
</p>

[Key Architecture](#-key-features--architecture) • [API Endpoints](#-api-endpoints-reference) • [Layered Design](#-layered-project-architecture) • [Getting Started](#-getting-started--setup) • [Configuration](#%EF%B8%8F-configuration)

</div>

---

## 📌 Overview

This project is a high-performance backend system built using **Java** and **Spring Boot** to power blogging platforms[cite: 1, 5]. It implements robust industry standards including stateless **JWT authentication**, **Spring Security** filters, **Data Transfer Object (DTO)** validation, and bidirectional JPA relationships with **MySQL** for managing blog posts, categories, and hierarchical user comments[cite: 1, 5].

---

## ⚡ Key Features & Architecture

- **🔐 Stateless Security & JWT:** End-to-end token-based security using Spring Security filters, providing stateless authentication with cryptographic token signature validation.
- **👥 Role-Based Access Control (RBAC):** Distinct permissions separating standard users (commenting/viewing) from publishers/administrators (content moderation and deletion)[cite: 1].
- **📦 Clean Layered Architecture:** Decoupled design using Controller, Service, Repository, DTO, and Entity layers ensuring clear separation of concerns[cite: 5].
- **🛡️ Strict Data Validation & DTO Pattern:** Request/response payloads decoupled from database entities to prevent over-posting and enforce schema sanitization[cite: 5].
- **🗄️ Relational Optimization:** JPA/Hibernate ORM mappings with cascading rules, indexed foreign keys, and lazy-loading protections to prevent N+1 query bottlenecks[cite: 1].
- **⚠️ Centralized Exception Handling:** Standardized error response structure using `@RestControllerAdvice` for validation errors, unauthorized attempts, and missing records.

---

## 🏛️ Layered Project Architecture

```mermaid
flowchart TD
    Client([HTTP Client / Postman / Frontend]) -->|HTTP Request / Bearer Token| Controller[Controller Layer]
    
    subgraph Spring Security Filter Chain
        Security[JwtAuthenticationFilter & SecurityConfig]
    end
    
    Client -.-> Security
    Security -.-> Controller
    
    Controller -->|DTO Validation| Service[Service Layer - Business Logic]
    Service -->|Entities| Repository[Repository Layer - Spring Data JPA]
    Repository -->|SQL Queries| DB[(MySQL Database)]
    
    style Controller fill:#6DB33F,stroke:#333,stroke-width:1px,color:#fff
    style Service fill:#24292e,stroke:#fff,stroke-width:1px,color:#fff
    style Repository fill:#4479A1,stroke:#333,stroke-width:1px,color:#fff
    style DB fill:#00758F,stroke:#333,stroke-width:1px,color:#fff
