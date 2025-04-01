package com.Cloudwave.Backend_AllCL.repository;
import com.Cloudwave.Backend_AllCL.entity.Ticket;
import com.Cloudwave.Backend_AllCL.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    boolean existsByUser(User user);
    // N+1 문제 방지
    @EntityGraph(attributePaths = {"user", "ticketInventory"})
    List<Ticket> findAll();
}
