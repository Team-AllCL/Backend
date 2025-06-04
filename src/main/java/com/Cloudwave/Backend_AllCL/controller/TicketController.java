package com.Cloudwave.Backend_AllCL.controller;

import com.Cloudwave.Backend_AllCL.dto.ticket.TicketRequestDto;
import com.Cloudwave.Backend_AllCL.dto.ticket.TicketResponseDto;
import com.Cloudwave.Backend_AllCL.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


//@CrossOrigin(origins = "http://dkgmp1k5bgam1.cloudfront.net")
@CrossOrigin(origins = "https://allcle-ticketing.click")

@RestController
@RequestMapping("/api/v1/ticketing")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<?> createTicket(@RequestBody TicketRequestDto ticketRequestDto) {
        System.out.println("[티켓 발급 요청] 사용자 이메일: " + ticketRequestDto.getEmail() +
                ", 티켓 이름: " + ticketRequestDto.getTicketName());

        try {
            ticketService.purchaseTicket(ticketRequestDto);
            // ✅ JSON 응답 객체 생성
            return ResponseEntity.ok().body(
                    Map.of("message", "SQS 메시지 전송 완료")
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "서버 오류"));
        }
    }


    @GetMapping
    public ResponseEntity<List<TicketResponseDto>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
}
