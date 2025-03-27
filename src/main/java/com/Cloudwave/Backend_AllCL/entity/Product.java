package com.Cloudwave.Backend_AllCL.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name="product")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 상품 이름
    @Column(nullable = false)
    private String name;

    // 상품 가격
    @Column(nullable = false)
    private int price;

    // 상품 재고
    @Column(nullable = false)
    private int stock;

    // 기본 생성자
    public Product() {}

    // 빌더 패턴
    @Builder
    public Product(String name, int price, int stock){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
