# StockFlow — Inventory & Procurement Management System

StockFlow is a full-stack inventory control starter application. It provides a responsive stock dashboard backed by a Spring Boot REST API and relational persistence, with Docker-based local development.

## Features

- Product catalogue with unique SKU validation
- Search by product name or SKU
- Stock-on-hand tracking and low-stock filtering
- Reorder thresholds and inventory valuation summary
- Stock receive/issue adjustments with negative-stock protection
- Supplier directory with duplicate-email protection and active/inactive status
- Multiple warehouse balances per SKU
- Transactional warehouse-to-warehouse stock transfers with transfer history
- Optimistic locking to reduce concurrent stock update conflicts
- Employee purchase requests with procurement approval/rejection
- Approval converts a request into a purchase order with reviewer traceability
- Purchase order creation with line-item pricing and supplier validation
- Goods receipt workflow that updates stock transactionally
- Immutable stock movement history for manual adjustments and purchase receipts
- JWT login, BCrypt password hashing and role-based endpoint authorization
- Roles: ADMIN, PROCUREMENT, WAREHOUSE, AUDITOR and EMPLOYEE
- Self-registration creates restricted EMPLOYEE accounts; only an administrator can grant elevated roles
- Add and delete product API operations
- Responsive dashboard with sign-in, role-aware inventory actions, procurement forms and transfer history
- CSV export for the currently filtered inventory view
- Administrator team-permissions panel and auditor stock-movement ledger
- H2 for quick local development; PostgreSQL for Docker Compose
- GitHub Actions build and test workflow

## Technology

- **Frontend:** React, TypeScript, Vite, Lucide
- **Backend:** Java 21, Spring Boot 3, Spring Data JPA, Spring Security, JWT and Bean Validation
- **Database:** H2 (local) / PostgreSQL 16 (Docker)
- **CI:** GitHub Actions

## Run with Docker

Requires Docker Desktop / Docker Engine with Compose.

```bash
git clone https://github.com/akshaykumar0079/StockFlow-Inventory-Procurement-Management-System.git
cd StockFlow-Inventory-Procurement-Management-System
cp .env.example .env  # set strong JWT_SECRET and SEED_ADMIN_PASSWORD values first\ndocker compose up --build
```

- Frontend: http://localhost:5173
- API: http://localhost:8080/api/products
- H2 console is enabled only for the default local H2 profile; Docker uses PostgreSQL.

## Run services separately

Backend (Java 21 and Maven):

```bash
cd backend
mvn spring-boot:run
```

Frontend (Node.js 22+):

```bash
cd frontend
npm install
npm run dev
```

The frontend uses `http://localhost:8080/api` by default. Override it with `VITE_API_URL`.

## Authentication and roles

- Login: `POST /api/auth/login` with `{"email":"admin@stockflow.local","password":"ChangeMe123!"}` on a fresh local database.
- Register: `POST /api/auth/register`; public registration always creates the low-privilege `EMPLOYEE` role.
- Send the returned access token as `Authorization: Bearer <token>` for protected endpoints.
- Local demo admin: `admin@stockflow.local` / `ChangeMe123!`.
- Override the initial admin password with `SEED_ADMIN_PASSWORD` before first startup. Override the signing key with a strong base64-encoded `JWT_SECRET` (at least 256 bits) and keep it private. If the admin account already exists, changing the environment variable does not reset its password.

Role access: ADMIN manages all modules; PROCUREMENT creates suppliers, approves requests and creates purchase orders; WAREHOUSE manages stock and receives purchase orders; AUDITOR can read stock movement history; EMPLOYEE has read-only access to standard authenticated endpoints.

## API

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/auth/register` | Register a restricted EMPLOYEE account |
| POST | `/api/auth/login` | Obtain a signed JWT access token |
| GET | `/api/products` | List products; supports `q` and `lowStock=true` |
| POST | `/api/products` | Create a product |
| PATCH | `/api/products/{id}/stock` | Receive or issue units with `{"change": 5, "reason": "Supplier delivery"}` |
| DELETE | `/api/products/{id}` | Delete a product |
| GET | `/api/products/summary` | SKU count, units, low-stock count and inventory value |
| GET / POST | `/api/suppliers` | List and create suppliers |
| PATCH | `/api/suppliers/{id}/status` | Activate or deactivate a supplier |
| GET / POST | `/api/purchase-orders` | List and create purchase orders |
| GET / POST | `/api/purchase-requests` | List and submit purchase requests |
| PATCH | `/api/purchase-requests/{id}/decision` | Approve/reject; approval creates a purchase order |
| POST | `/api/purchase-orders/{id}/receive` | Receive an order and add ordered units to stock |
| GET | `/api/stock-movements` | View inventory movement history |
| GET | `/api/warehouses` | List warehouse names in use |
| GET / POST | `/api/warehouse-transfers` | List transfer history and transfer stock between warehouses |
| GET | `/api/admin/users` | Admin-only user list |
| PATCH | `/api/admin/users/{id}/role` | Grant an application role |
| PATCH | `/api/admin/users/{id}/status` | Enable or disable an account |

Example product payload:

```json
{
  "name": "Thermal Label Roll",
  "sku": "LBL-110",
  "category": "Packaging",
  "warehouse": "Chennai Central",
  "quantity": 40,
  "reorderLevel": 12,
  "unitPrice": 180
}
```

## Current scope and next milestones

This repository implements an inventory catalogue, supplier directory, purchase-order creation and receiving, and stock movement history. Purchase request/approval queues, richer warehouse master-data management, and production deployment hardening remain future milestones. The current app is a portfolio MVP, not a production-ready ERP. The local default admin password and fallback JWT key are development conveniences only; override both before any shared or deployed use. Do not expose it to real business data until those controls are implemented.

## Tests

- Backend: `cd backend && mvn test`
- Frontend: `cd frontend && npm install && npm run build`

The GitHub Actions workflow runs both checks on pushes and pull requests to `main`.
