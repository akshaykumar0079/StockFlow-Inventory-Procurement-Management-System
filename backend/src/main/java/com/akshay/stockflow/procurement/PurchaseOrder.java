package com.akshay.stockflow.procurement;
import jakarta.persistence.*; import java.time.Instant; import java.util.*;
@Entity @Table(name="purchase_orders") public class PurchaseOrder {
public enum Status { ORDERED, PARTIALLY_RECEIVED, RECEIVED }
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Version private long version; @Column(nullable=false) private Long supplierId; @Column(nullable=false) private String supplierName; @Enumerated(EnumType.STRING) @Column(nullable=false) private Status status=Status.ORDERED; @Column(nullable=false) private double total; @Column(nullable=false,updatable=false) private Instant createdAt=Instant.now(); @ElementCollection(fetch=FetchType.EAGER) @CollectionTable(name="purchase_order_lines",joinColumns=@JoinColumn(name="purchase_order_id")) private List<PurchaseOrderLine> lines=new ArrayList<>();
protected PurchaseOrder(){} public PurchaseOrder(Long supplierId,String supplierName,List<PurchaseOrderLine> lines){this.supplierId=supplierId;this.supplierName=supplierName;this.lines=new ArrayList<>(lines);this.total=lines.stream().mapToDouble(l->l.getQuantity()*l.getUnitCost()).sum();}
public Long getId(){return id;} public Long getSupplierId(){return supplierId;} public String getSupplierName(){return supplierName;} public Status getStatus(){return status;} public double getTotal(){return total;} public Instant getCreatedAt(){return createdAt;} public List<PurchaseOrderLine> getLines(){return Collections.unmodifiableList(lines);} public void setStatus(Status v){status=v;}
}
