package com.akshay.stockflow.product;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface ProductRepository extends JpaRepository<Product,Long>{boolean existsBySku(String sku); List<Product> findByNameContainingIgnoreCaseOrSkuContainingIgnoreCase(String name,String sku);}
