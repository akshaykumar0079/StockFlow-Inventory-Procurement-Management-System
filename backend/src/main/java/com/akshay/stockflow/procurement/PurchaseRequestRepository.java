package com.akshay.stockflow.procurement;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest,Long>{List<PurchaseRequest> findAllByOrderByCreatedAtDesc(); List<PurchaseRequest> findAllByRequestedByOrderByCreatedAtDesc(String requestedBy);}
