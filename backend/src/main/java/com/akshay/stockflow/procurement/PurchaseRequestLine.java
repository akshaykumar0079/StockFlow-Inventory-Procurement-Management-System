package com.akshay.stockflow.procurement;
import jakarta.persistence.*;
@Embeddable public class PurchaseRequestLine {
private Long productId; private String sku; private String productName; private int quantity; private double unitCost;
protected PurchaseRequestLine(){} public PurchaseRequestLine(Long productId,String sku,String productName,int quantity,double unitCost){this.productId=productId;this.sku=sku;this.productName=productName;this.quantity=quantity;this.unitCost=unitCost;}
public Long getProductId(){return productId;} public String getSku(){return sku;} public String getProductName(){return productName;} public int getQuantity(){return quantity;} public double getUnitCost(){return unitCost;}
}
