package com.akshay.stockflow.warehouse;
import com.akshay.stockflow.product.*; import com.akshay.stockflow.audit.*; import jakarta.transaction.Transactional; import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.http.HttpStatus; import org.springframework.web.bind.annotation.*; import org.springframework.web.server.ResponseStatusException; import java.util.List;
@RestController @RequestMapping("/api/warehouse-transfers") public class WarehouseTransferController {
private final ProductRepository products; private final WarehouseTransferRepository transfers; private final StockMovementRepository movements;
public WarehouseTransferController(ProductRepository products,WarehouseTransferRepository transfers,StockMovementRepository movements){this.products=products;this.transfers=transfers;this.movements=movements;}
@GetMapping public List<WarehouseTransfer> all(){return transfers.findAllByOrderByTransferredAtDesc();}
@PostMapping @ResponseStatus(HttpStatus.CREATED) @Transactional public WarehouseTransfer transfer(@Valid @RequestBody TransferInput input){
Product source=products.findById(input.productId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Source inventory item not found"));
if(source.getWarehouse().equalsIgnoreCase(input.destinationWarehouse()))throw new ResponseStatusException(HttpStatus.CONFLICT,"Choose a different destination warehouse");
if(source.getQuantity()<input.quantity())throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Insufficient stock at source warehouse");
String destinationName=input.destinationWarehouse().trim();Product destination=products.findBySkuAndWarehouse(source.getSku(),destinationName).orElseGet(()->products.save(new Product(source.getName(),source.getSku(),source.getCategory(),destinationName,0,source.getReorderLevel(),source.getUnitPrice())));
int sourceBefore=source.getQuantity(),destinationBefore=destination.getQuantity();source.setQuantity(sourceBefore-input.quantity());destination.setQuantity(destinationBefore+input.quantity());products.save(source);products.save(destination);
movements.save(new StockMovement(source.getId(),source.getSku(),source.getWarehouse(),"TRANSFER_OUT",sourceBefore,-input.quantity(),source.getQuantity(),"WH-"+destinationName));
movements.save(new StockMovement(destination.getId(),destination.getSku(),destination.getWarehouse(),"TRANSFER_IN",destinationBefore,input.quantity(),destination.getQuantity(),"WH-"+source.getWarehouse()));
return transfers.save(new WarehouseTransfer(source.getSku(),source.getName(),source.getWarehouse(),destinationName,input.quantity(),source.getId(),destination.getId()));
}
public record TransferInput(@NotNull Long productId,@NotBlank @Size(max=100) String destinationWarehouse,@Min(1) @Max(1000000) int quantity){}
}
