package com.akshay.stockflow.warehouse;
import com.akshay.stockflow.product.ProductRepository; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/warehouses") public class WarehouseController {
private final ProductRepository products; public WarehouseController(ProductRepository products){this.products=products;}
@GetMapping public List<String> all(){return products.findAll().stream().map(p->p.getWarehouse()).distinct().sorted().toList();}
}
