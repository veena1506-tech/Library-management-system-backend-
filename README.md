# Library Management System — Backend

## Requirements
- Java 17+
- Maven 3.9+ (or use your IDE's Maven support)

## Run
From this folder:
```bash
mvn spring-boot:run
```
API runs at `http://localhost:8080`. H2 console: `http://localhost:8080/h2-console`
(JDBC URL: `jdbc:h2:file:./data/librarydb`, user `sa`, blank password).
The server uses the hosting platform's `PORT` environment variable when present (default `8080`).
Set `CORS_ALLOWED_ORIGIN` to the frontend's exact deployed origin; it defaults to `http://localhost:5173`.

## Run with Docker
Build and start the backend from this folder:
```bash
docker build -t library-management-backend .
docker run --name library-management-backend -p 8080:8080 -v library-management-data:/app/data library-management-backend
```
The named Docker volume keeps the H2 database between container restarts. The API is available at `http://localhost:8080`.

## REST API
- `GET /api/books` — list all books
- `GET /api/books/{id}` — get one book
- `POST /api/books` — create a book
- `PUT /api/books/{id}` — update a book
- `DELETE /api/books/{id}` — delete a book

Example JSON:
```json
{"title":"Atomic Habits","author":"James Clear","isbn":"9780735211292","category":"Self-help","quantity":5}
```
The database is local H2 and persists to `data/` after the first run.
