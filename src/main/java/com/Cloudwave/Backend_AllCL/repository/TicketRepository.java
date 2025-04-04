package com.Cloudwave.Backend_AllCL.repository;
import com.Cloudwave.Backend_AllCL.entity.Ticket;
import com.Cloudwave.Backend_AllCL.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    // 사용자 중복 방지를 email을 기준으로 판단
    boolean existsByUser(User user);
    // N+1 문제 방지
    @EntityGraph(attributePaths = {"user", "ticketInventory"})
    List<Ticket> findAll();
}
