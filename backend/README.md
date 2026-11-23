# StockFlow API

Spring Boot REST API for inventory, suppliers, purchase orders, warehouse transfers and audit history.

## Run locally

Requires Java 21 and Maven:

```bash
mvn spring-boot:run
```

The default profile uses an in-memory H2 database. API base URL: `http://localhost:8080/api`.

## Local administrator

A fresh database seeds:

- Email: `admin@stockflow.local`
- Password: `ChangeMe123!`

Override `SEED_ADMIN_PASSWORD` before first startup. The seed password only applies when the admin account is first created. Override `JWT_SECRET` with a strong base64-encoded key of at least 256 bits outside local development.

## Authentication

- `POST /api/auth/login` returns a bearer JWT.
- `POST /api/auth/register` always creates an EMPLOYEE account.
- Send `Authorization: Bearer <token>` to protected routes.
- Admins can grant roles through `/api/admin/users/{id}/role`.

Roles: ADMIN, PROCUREMENT, WAREHOUSE, AUDITOR and EMPLOYEE.

## Endpoints

- `GET /api/products`, `POST /api/products`, `PATCH /api/products/{id}/stock`, `DELETE /api/products/{id}`
- `GET /api/products/summary`
- `GET /api/suppliers`, `POST /api/suppliers`, `PATCH /api/suppliers/{id}/status`
- `GET /api/purchase-orders`, `POST /api/purchase-orders`, `POST /api/purchase-orders/{id}/receive`
- `GET /api/warehouses`, `GET /api/warehouse-transfers`, `POST /api/warehouse-transfers`
- `GET /api/stock-movements`
- `GET /api/admin/users`, `PATCH /api/admin/users/{id}/role`, `PATCH /api/admin/users/{id}/status`

PostgreSQL can be configured with `DB_URL`, `DB_USER`, and `DB_PASSWORD`.

## Tests

```bash
mvn test
```
