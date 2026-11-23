package com.akshay.stockflow.audit;
import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/stock-movements") public class StockMovementController {private final StockMovementRepository movements; public StockMovementController(StockMovementRepository movements){this.movements=movements;} @GetMapping public List<StockMovement> all(){return movements.findAllByOrderByOccurredAtDesc();}}
