package com.Cloudwave.Backend_AllCL.controller;

import com.Cloudwave.Backend_AllCL.dto.order.OrderRequestDto;
import com.Cloudwave.Backend_AllCL.dto.order.OrderResponseDto;
import com.Cloudwave.Backend_AllCL.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin(origins = "http://dkgmp1k5bgam1.cloudfront.net")
@CrossOrigin(origins = "https://allcle-order.click")

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        // 프론트에서 전달된 값 로그 출력
        System.out.println("[주문 요청] 상품: " + orderRequestDto.getProductName() + ", 이메일: " + orderRequestDto.getUserEmail());

        try {
            orderService.placeOrder(orderRequestDto, orderRequestDto.getUserEmail());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace(); // 콘솔에 에러 출력
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<OrderResponseDto> getLatestOrder() {
        return ResponseEntity.ok(orderService.getLatestOrder());
    }
}
