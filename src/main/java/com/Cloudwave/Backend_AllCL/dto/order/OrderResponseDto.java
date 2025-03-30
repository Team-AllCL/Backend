package com.Cloudwave.Backend_AllCL.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderResponseDto {
    private String productName;
    private String userEmail;
    private LocalDateTime createdAt;
}