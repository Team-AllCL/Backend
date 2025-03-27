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

    // 티켓 이름(ex. 페스타)
    @Column(nullable = false)
    private String name;

    // 티켓 가격
    @Column(nullable = false)
    private int price;

    // 티켓 재고
    @Column(nullable = false)
    private int stock;

    // 티켓 구매한 사용자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 티켓 구매 시간
    // @Column(nullable = false)
    // private LocalDateTime createdAt;

    // 기본 생성자
    public Ticket() {}

    // 빌더 패턴
    @Builder
    public Ticket(String name, int price, int stock, User user, LocalDateTime purchaseTime) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.user = user;
        // this.createdAt = createdAt;
    }
}
