# Lab7 Microservices Project

This project is a small Spring Boot microservices demo with Eureka service discovery, an API Gateway, and several services backed by PostgreSQL/H2. This README explains how to build, run, test and troubleshoot the system using Docker Compose or locally with Maven.

---

## Contents
- Overview
- Prerequisites
- Ports and services
- Start (Docker Compose)
- Verify (Eureka / health)
- Example API requests (Postman / curl)
- Running services locally
- Troubleshooting
- Useful file locations

---

## Overview
Services included (lab7):
- `eureka-server` — service registry (port 8761)
- `api-gateway` — gateway (port 8080)
- `student-service` — students (port 8081)
- `course-service` — courses (port 8082)
- `professor-service` — professors (port 8083)
- `grading-service` — grades (port 8084)
- `postgres` — PostgreSQL DB (port 5432)

The system is orchestrated by `docker-compose.yml` under the `lab7/` folder.

## Prerequisites
- Docker (desktop or engine) installed and running
- Docker Compose (v2 recommended) — `docker compose` CLI
- Optional: Postman or curl for API testing

## Ports (local)
- Eureka UI: http://localhost:8761
- API Gateway: http://localhost:8080
- Postgres (DB): localhost:5432

## Start with Docker Compose
From the `lab7` directory run:
```bash
cd lab7
# build and run in foreground
docker compose up --build

# or run detached
docker compose up --build -d
```

Stop and remove containers (and volumes):
```bash
docker compose down --volumes
```

## Verify services and Eureka
- Open Eureka UI: http://localhost:8761 — you should see registered services after they start.
- Query Eureka raw apps JSON:
```bash
curl http://localhost:8761/eureka/apps
```
- Tail a service log:
```bash
docker compose logs -f student-service
```

## API entry point and routes
Use the API Gateway as a single entrypoint: `http://localhost:8080`.
The gateway forwards requests to backend services. Common routes (examples):
- `/students` -> `student-service`
- `/courses` -> `course-service`
- `/professors` -> `professor-service`
- `/grades` -> `grading-service`

Note: the gateway routes are configured in the gateway's `application.properties`.

## Example requests (Postman / curl)
Base URL: `http://localhost:8080`

- List students
  - GET `/students`
  - curl:
    ```bash
    curl http://localhost:8080/students
    ```

- Get student by id
  - GET `/students/{id}`
  - curl:
    ```bash
    curl http://localhost:8080/students/1
    ```

- Create student
  - POST `/students`
  - Headers: `Content-Type: application/json`
  - Body example:
    ```json
    {
      "firstName": "Alice",
      "lastName": "Smith",
      "email": "alice@example.com",
      "address": "123 Main St"
    }
    ```
  - curl:
    ```bash
    curl -X POST -H "Content-Type: application/json" -d '{"firstName":"Alice","lastName":"Smith","email":"alice@example.com","address":"123 Main St"}' http://localhost:8080/students
    ```

- Student report-card (calls grading service)
  - GET `/students/{id}/report-card`
  - curl:
    ```bash
    curl http://localhost:8080/students/1/report-card
    ```

Use Postman: create a collection, set environment variable `baseUrl = http://localhost:8080`, then add requests using the paths above.

## Running services locally (without Docker)
You can run any service from its module folder with Maven. Example for student-service:
```bash
cd lab7/student-service/student-service
mvn spring-boot:run
```
If running services locally, ensure `eureka.client.service-url.defaultZone` points to the running Eureka (e.g. `http://localhost:8761/eureka/`).

## Database setup
- The Compose file runs a `postgres:15-alpine` container mapped to port `5432`.
- Initialization SQL is at `lab7/docker-setup/init.sql` — it creates the required databases:
  - `student_db`
  - `course_db`
  - `professor_db`

Connection details used by services via environment variables in `docker-compose.yml`:
- `SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/<db>`
- `SPRING_DATASOURCE_USERNAME=postgres`
- `SPRING_DATASOURCE_PASSWORD=password`

Services using PostgreSQL set `spring.jpa.hibernate.ddl-auto=update` so tables are created/updated automatically at startup. If you prefer manual migrations, integrate Flyway or Liquibase and change the property.

## Health & Actuator endpoints
- Each service exposes actuator endpoints. Examples:
  - Health: `http://localhost:8080/<service-base>/actuator/health` (gateway will route)
  - Info: `http://localhost:8080/<service-base>/actuator/info`

Eureka health checks rely on these actuator endpoints.

## Troubleshooting
- Service not registering in Eureka:
  - Check Eureka is UP: `curl http://localhost:8761/actuator/health` and the UI.
  - Ensure `EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE` or `eureka.client.service-url.defaultZone` points to `http://eureka-server:8761/eureka/` when running in Docker.
  - Inspect service logs: `docker compose logs -f <service-name>`.

- Database errors (table not found / relation does not exist):
  - Ensure Postgres container is healthy and `init.sql` ran.
  - Check service DB URL environment and that `spring.jpa.hibernate.ddl-auto` is configured as desired.

- Build issues:
  - Rebuild images: `docker compose up --build`.
  - Remove stale artifacts: `docker compose down --rmi local --volumes` then rebuild.

## Useful files
- Compose: `lab7/docker-compose.yml`
- DB init script: `lab7/docker-setup/init.sql`
- Student controller: `lab7/student-service/student-service/src/main/java/com/example/student_service/StudentController.java`
- API Gateway config: `lab7/api-gateway/api-gateway/src/main/resources/application.properties`
- Dockerfiles: `lab7/*/*/Dockerfile` (each service folder contains its Dockerfile)

## Development notes
- The codebase had inconsistent Spring Boot / Spring Cloud versions; they were normalized to Boot 3.x and Cloud 2023.x in the current working copy to avoid runtime ClassNotFound errors.
- Actuator endpoints were added/exposed to allow health checks and Eureka detection.

## Next steps (suggested)
- Optional: add CI pipeline to build images and run integration tests.
- Add proper database migrations (Flyway/Liquibase) for production use.

---

If you want, I can:
- Commit this `README.md` to the repository now.
- Start `docker compose up --build` and stream logs while you watch.

File created: `lab7/README.md`
