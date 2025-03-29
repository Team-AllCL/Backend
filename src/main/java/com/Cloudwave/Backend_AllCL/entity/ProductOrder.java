package com.Cloudwave.Backend_AllCL.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDate;


@Entity
@Getter
@Table(name="productOrder")
public class ProductOrder extends BaseEntity {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 주문 사용자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 주문 상품 - 하나의 상품만 주문한다고 가정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 주문 시간
    // @Column(nullable = false)
    // private LocalDate orderDate;

    // 기본 생성자
    public ProductOrder() {}

    // 빌더 패턴
    @Builder
    public ProductOrder(User user, Product product, LocalDate orderDate) {
        this.user = user;
        this.product = product;
        // this.orderDate = orderDate;
    }

}
