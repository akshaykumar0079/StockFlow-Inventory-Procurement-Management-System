package com.akshay.stockflow.product;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import java.time.Instant;
@Entity @Table(name="products",uniqueConstraints=@UniqueConstraint(columnNames={"sku","warehouse"})) public class Product {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Version private long version;
@NotBlank @Column(nullable=false) private String name; @NotBlank @Column(nullable=false) private String sku; @NotBlank @Column(nullable=false) private String category; @NotBlank @Column(nullable=false) private String warehouse; @Min(0) private int quantity; @Min(0) private int reorderLevel; @DecimalMin("0.0") private double unitPrice; @Column(nullable=false,updatable=false) private Instant createdAt=Instant.now();
protected Product(){} public Product(String name,String sku,String category,String warehouse,int quantity,int reorderLevel,double unitPrice){this.name=name;this.sku=sku;this.category=category;this.warehouse=warehouse;this.quantity=quantity;this.reorderLevel=reorderLevel;this.unitPrice=unitPrice;}
public Long getId(){return id;} public String getName(){return name;} public String getSku(){return sku;} public String getCategory(){return category;} public String getWarehouse(){return warehouse;} public int getQuantity(){return quantity;} public int getReorderLevel(){return reorderLevel;} public double getUnitPrice(){return unitPrice;} public Instant getCreatedAt(){return createdAt;} public void setQuantity(int v){quantity=v;}
}
