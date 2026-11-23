package com.akshay.stockflow.warehouse;
import org.springframework.data.jpa.repository.JpaRepository; import java.util.List;
public interface WarehouseTransferRepository extends JpaRepository<WarehouseTransfer,Long>{List<WarehouseTransfer> findAllByOrderByTransferredAtDesc();}
