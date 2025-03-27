package com.Cloudwave.Backend_AllCL.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name="user")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이메일
    @Column(nullable = false, unique = true)
    private String email;

    // 비밀번호
    @Column(nullable = false)
    private String password;

    // 기본 생성자
    public User(){}

    // 빌더 패턴
    @Builder
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
