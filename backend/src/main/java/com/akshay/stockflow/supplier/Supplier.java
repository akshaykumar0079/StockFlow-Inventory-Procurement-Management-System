package com.akshay.stockflow.supplier;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import java.time.Instant; import java.util.Locale;
@Entity @Table(name="suppliers") public class Supplier {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @NotBlank @Column(nullable=false) private String name; @NotBlank @Column(nullable=false,unique=true) private String email; private String phone; private String contactName; private String address; @Column(nullable=false) private boolean active=true; @Column(nullable=false,updatable=false) private Instant createdAt=Instant.now();
protected Supplier(){} public Supplier(String name,String email,String phone,String contactName,String address){this.name=name;this.email=email.toLowerCase(Locale.ROOT);this.phone=phone;this.contactName=contactName;this.address=address;}
public Long getId(){return id;} public String getName(){return name;} public String getEmail(){return email;} public String getPhone(){return phone;} public String getContactName(){return contactName;} public String getAddress(){return address;} public boolean isActive(){return active;} public void setActive(boolean v){active=v;}
}
