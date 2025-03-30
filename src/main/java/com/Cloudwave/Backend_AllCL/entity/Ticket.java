package com.Cloudwave.Backend_AllCL.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="ticket")
public class Ticket extends BaseEntity {

    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 티켓 구매한 사용자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", nullable = false)
    private TicketInventory ticketInventory;

    // 기본 생성자
    public Ticket() {}

    // 빌더 패턴
    @Builder
    public Ticket(User user, TicketInventory ticketInventory) {
        this.user = user;
        this.ticketInventory = ticketInventory;
    }

}
