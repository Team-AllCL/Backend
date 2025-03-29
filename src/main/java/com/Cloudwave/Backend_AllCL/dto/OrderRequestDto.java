package com.Cloudwave.Backend_AllCL.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {
    private String productName;
    private String userEmail;
}
