package com.Cloudwave.Backend_AllCL.repository;

import com.Cloudwave.Backend_AllCL.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {
}