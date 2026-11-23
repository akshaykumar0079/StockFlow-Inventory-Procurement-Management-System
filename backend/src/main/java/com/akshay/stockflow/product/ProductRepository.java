package com.akshay.stockflow.product;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.List; import java.util.Optional;
public interface ProductRepository extends JpaRepository<Product,Long>{boolean existsBySkuAndWarehouse(String sku,String warehouse); Optional<Product> findBySkuAndWarehouse(String sku,String warehouse); List<Product> findByNameContainingIgnoreCaseOrSkuContainingIgnoreCase(String name,String sku);}
