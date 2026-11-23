# StockFlow API

Spring Boot REST API for inventory catalogue operations.

## Run locally

```bash
mvn spring-boot:run
```

The default profile uses an in-memory H2 database. API base URL: `http://localhost:8080/api`.

## Endpoints

- `GET /products` — list products; optional `q` and `lowStock=true` filters
- `POST /products` — create a product
- `PATCH /products/{id}/stock` — adjust stock using `{"change": 5}` or a negative change
- `DELETE /products/{id}` — remove a product
- `GET /products/summary` — SKU, unit, low-stock and inventory-value summary

PostgreSQL can be configured with `DB_URL`, `DB_USER`, and `DB_PASSWORD`.
