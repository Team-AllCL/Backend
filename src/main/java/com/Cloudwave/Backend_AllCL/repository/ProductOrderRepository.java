package com.Cloudwave.Backend_AllCL.repository;

import com.Cloudwave.Backend_AllCL.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
    @Query(value = "SELECT * FROM product_order ORDER BY created_at DESC LIMIT 1", nativeQuery = true)
    ProductOrder findLatestOrder();
}
