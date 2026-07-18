# Inventory Management System (Multi-Tenant) 🚀

## Table of Contents

- [Overview](#overview)
- [Architecture Highlights](#architecture-highlights)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Entity Relationship Diagram](#entity-relationship-diagram)
- [System Design Specification](#system-design-specification)
- [API Documentation](#api-documentation)

---
<a id="overview"></a>
## Overview ✨

Inventory Management System (IMS) is a multi-tenant inventory platform that serves as the authoritative source of truth for inventory operations across commerce ecosystems.

IMS is designed for inventory-driven applications such as e-commerce platforms, marketplaces, ERP systems, and order management solutions. By centralizing inventory ownership, stock allocation, and reservation management, consuming systems can focus on their core business functionality while delegating inventory consistency, stock tracking, and overselling prevention to IMS.

This platform enables organizations to manage its inventory through a dedicated dashboard that provides visibility and control over organization settings, stock items, stock batches trucking, inventory availability, reservation workflows , and inventory history. In addition, IMS exposes secure machine-to-machine APIs that allow external systems to integrate inventory workflows directly into their business processes.

This platform provides reservation-based inventory operations workflows through secure machine-to-machine APIs for reserving, confirming, and releasing stock, enabling reliable ccritical business processes such as checkout and order fulfillment across distributed systems.

As a multi-tenant solution, IMS supports organization-level inventory ownership that maintaining tenant isolation, actor-based abstraction, auditing, and secure system-to-system integrations, providing a scalable foundation for organizations that require centralized and reliable inventory management.



---
<a id="architecture-highlights"></a>
## Architecture Highlights 🏗️

- Modular Monolith Architecture
- Domain-Driven Design (DDD)
- Multi-Tenant Inventory Ownership Model
- Organization-Scoped Data Isolation
- Reservation-Based Inventory Management
- Machine-to-Machine Integration APIs
- Actor-Based Identity Architecture
- Role-Based Access Control (RBAC)
- Capability-Based Authorization
- Inventory System as Source of Truth
- Structured Logging & Request Tracing
- Entity Auditing & Business Event Tracking
- Scheduled Reservation Expiration Processing
- Integration-Ready Service Boundaries



---
<a id="features"></a>
## Features ✨


### Identity & Access Management

Manage platform users and secure access to inventory operations.

- Account registration and activation by sending activation code via email
- JWT-based authentication
- Role-Based Access Control (RBAC)
- Capability-based authorization
- View account profile
- Update account profile

---

### Organization Management

Manage tenant organizations and their operational configuration.

- View organization details
- Update organization profile
- Configure organization settings 
- Manage reservation expiration policies
- Configure stock allocation strategies
- Create machine integration clients
- Rotate client credentials

---

### Stock Management

Manage inventory ownership, stock visibility, and inventory replenishment.

- Create stock items
- Update stock items
- Soft delete stock items
- View stock item details
- List stock items with pagination
- Restock inventory
- Track inventory quantities and availability
- View stock batch history
- List stock batches with pagination and filtration

---

### Machine-to-Machine Authentication

Enable secure system-to-system communication for external inventory consumers.

- Client credential authentication
- JWT access token generation
- Organization-scoped machine identities
- Capability-based machine authorization

---

### Inventory Reservation Workflows

Provide reservation-based inventory operations for distributed commerce systems.

- Reserve inventory during checkout
- Confirm reservations after successful payment
- Release reservations when checkout or payment fails
- Automatic clean up for expired reservations according to the predefined organization (tenant) settings
- Overselling prevention through temporary stock locking

---

### Multi-Tenant Architecture

Support multiple organizations on a shared platform while maintaining strict isolation.

- Organization-based inventory ownership
- Tenant-scoped inventory operations
- Tenant-scoped authorization
- Tenant-aware reservation management

---

### Observability & Auditability 

Provide operational visibility and traceability across all inventory workflows.

- Structured logging
- Request tracing
- Security event structured logging
- Business event structured logging
- Exceptions structured Logging
- Actor-based activity tracking
- Entity lifecycle auditing


---
<a id="technologies-used"></a>
## Technologies Used 🛠️


### Backend & Framework

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate

---

### Database & Persistence

- MySQL
- JPA / Hibernate ORM

---

### Authentication & Authorization

- JWT Authentication
- Role-Based Access Control (RBAC)
- Capability-Based Authorization

---

### API & Documentation

- REST APIs
- OpenAPI 3
- Swagger UI
- SpringDoc OpenAPI

---

### Architecture & Design

- Modular Monolith Design
- Domain-Driven Design (DDD)
- Clean Architecture
- Layered Architecture
- Multi-Tenant Architecture
- Bounded Context Integration
- Actor-Based Abstraction Identity Model

---

### Observability & Auditing

- SLF4J
- Logback
- Structured JSON Logging
- Request Correlation & Tracing
- Spring Data JPA Auditing

---

### Integration & Communication

- Machine-to-Machine APIs
- JWT Client Authentication
- Service Integration Gateway Pattern
- Anti-Corruption Layer Pattern

---

### Developer Productivity

- Maven
- Lombok
- MapStruct

---

### Email & Notifications

- Java Mail
- Thymeleaf Email Templates

---

### Infrastructure 

- Docker
- Docker Compose

---
<a id="entity-relationship-diagram"></a>
## Entity Relationship Diagram 🗄️

The following Entity Relationship Diagram (ERD) represents the core domain model of the Inventory Management System.

![Inventory Management System ERD](docs/diagrams/ims-erd.jpg)

---
<a id="system-design-specification"></a>
## System Design Specification 📘

A detailed architectural specification describing the platform architecture, domain model, inventory reservation workflows, multi-tenant design, integration architecture, consistency strategies, and operational considerations is available below.

📄 [Inventory Management System Specification](docs/specifications/inventory-management-system-specification.pdf)



---
<a id="api-documentation"></a>
## API Documentation 📑

The Inventory Management System exposes a fully documented OpenAPI 3 specification covering authentication, organization management, stock management, reservation workflows, and machine-to-machine integrations.

### Swagger Documentation Previews

- 📷 [Swagger-UI APIs Overview](docs/api/swagger-overview.jpeg)
- 📷 [Accounts (human-actor) Authentication APIs](docs/api/human-authentication.jpeg)
- 📷 [Clients (machine-actor) Authentication APIs](docs/api/machine-authentication.jpeg)
- 📷 [Organization Management APIs](docs/api/organization-management.jpeg)
- 📷 [Stock Management APIs](docs/api/stock-management.jpeg)
- 📷 [Reservation Management APIs](docs/api/reservation-management.jpeg)
