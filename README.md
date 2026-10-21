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
