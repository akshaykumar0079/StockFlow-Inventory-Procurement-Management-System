package com.akshay.stockflow.supplier;
import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.http.HttpStatus; import org.springframework.web.bind.annotation.*; import org.springframework.web.server.ResponseStatusException; import java.util.List;
@RestController @RequestMapping("/api/suppliers") public class SupplierController {
private final SupplierRepository suppliers; public SupplierController(SupplierRepository suppliers){this.suppliers=suppliers;}
@GetMapping public List<Supplier> all(){return suppliers.findAll();}
@PostMapping @ResponseStatus(HttpStatus.CREATED) public Supplier create(@Valid @RequestBody SupplierInput i){if(suppliers.existsByEmailIgnoreCase(i.email()))throw new ResponseStatusException(HttpStatus.CONFLICT,"Supplier email already exists");return suppliers.save(new Supplier(i.name(),i.email(),i.phone(),i.contactName(),i.address()));}
@PatchMapping("/{id}/status") public Supplier status(@PathVariable Long id,@RequestBody StatusInput i){Supplier s=suppliers.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Supplier not found"));s.setActive(i.active());return suppliers.save(s);}
public record SupplierInput(@NotBlank String name,@Email @NotBlank String email,String phone,String contactName,String address){} public record StatusInput(boolean active){}
}
