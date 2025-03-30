package com.Cloudwave.Backend_AllCL.repository;

import com.Cloudwave.Backend_AllCL.entity.TicketInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketInventoryRepository extends JpaRepository <TicketInventory, Long> {
    Optional<TicketInventory> findByName(String name);
}
