package com.Cloudwave.Backend_AllCL.repository;
import com.Cloudwave.Backend_AllCL.entity.Ticket;
import com.Cloudwave.Backend_AllCL.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    boolean existsByUser(User user);
}
