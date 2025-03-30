package com.Cloudwave.Backend_AllCL.service;

import com.Cloudwave.Backend_AllCL.dto.OrderRequestDto;
import com.Cloudwave.Backend_AllCL.entity.ProductOrder;
import com.Cloudwave.Backend_AllCL.entity.User;
import com.Cloudwave.Backend_AllCL.repository.ProductOrderRepository;
import com.Cloudwave.Backend_AllCL.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductOrderRepository productOrderRepository;
    private final UserRepository userRepository;

    public void placeOrder(OrderRequestDto orderRequestDto, String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다"));

        ProductOrder order = ProductOrder.builder()
                .user(user)
                .productName(orderRequestDto.getProductName())
                .build();

        productOrderRepository.save(order);
    }
}