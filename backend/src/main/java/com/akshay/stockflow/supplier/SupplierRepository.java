package com.akshay.stockflow.supplier;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SupplierRepository extends JpaRepository<Supplier,Long>{boolean existsByEmailIgnoreCase(String email);}
