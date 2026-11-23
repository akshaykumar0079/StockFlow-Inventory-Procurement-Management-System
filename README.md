# StockFlow — Inventory & Procurement Management System

StockFlow is a full-stack inventory control starter application. It provides a responsive stock dashboard backed by a Spring Boot REST API and relational persistence, with Docker-based local development.

## Features

- Product catalogue with unique SKU validation
- Search by product name or SKU
- Stock-on-hand tracking and low-stock filtering
- Reorder thresholds and inventory valuation summary
- Stock receive/issue adjustments with negative-stock protection
- Add and delete product API operations
- Responsive dashboard with demo data when the API is unavailable
- H2 for quick local development; PostgreSQL for Docker Compose
- GitHub Actions build and test workflow

## Technology

- **Frontend:** React, TypeScript, Vite, Lucide
- **Backend:** Java 21, Spring Boot 3, Spring Data JPA, Bean Validation
- **Database:** H2 (local) / PostgreSQL 16 (Docker)
- **CI:** GitHub Actions

## Run with Docker

Requires Docker Desktop / Docker Engine with Compose.

```bash
git clone https://github.com/akshaykumar0079/StockFlow-Inventory-Procurement-Management-System.git
cd StockFlow-Inventory-Procurement-Management-System
docker compose up --build
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

## API

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/products` | List products; supports `q` and `lowStock=true` |
| POST | `/api/products` | Create a product |
| PATCH | `/api/products/{id}/stock` | Receive or issue units with `{"change": 5}` |
| DELETE | `/api/products/{id}` | Delete a product |
| GET | `/api/products/summary` | SKU count, units, low-stock count and inventory value |

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

This repository currently implements the inventory catalogue and stock adjustment vertical slice. Supplier management, purchase requests/approvals, purchase orders, goods receiving workflows, authentication/RBAC, warehouse transfers, and audit-log persistence are not yet implemented. Do not use the current demo as a production inventory system until those controls and deployment hardening are completed.

## Tests

- Backend: `cd backend && mvn test`
- Frontend: `cd frontend && npm install && npm run build`

The GitHub Actions workflow runs both checks on pushes and pull requests to `main`.
