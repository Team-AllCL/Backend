package com.Cloudwave.Backend_AllCL.controller;

import com.Cloudwave.Backend_AllCL.dto.OrderRequestDto;
import com.Cloudwave.Backend_AllCL.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        orderService.placeOrder(orderRequestDto, orderRequestDto.getUserEmail());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<String> getOrders() {
        return ResponseEntity.ok("주문 조회 API는 아직 구현되지 않았습니다.");
    }
}
