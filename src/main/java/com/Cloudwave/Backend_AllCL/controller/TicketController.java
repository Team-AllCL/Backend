package com.Cloudwave.Backend_AllCL.controller;

import com.Cloudwave.Backend_AllCL.dto.ticket.TicketRequestDto;
import com.Cloudwave.Backend_AllCL.dto.ticket.TicketResponseDto;
import com.Cloudwave.Backend_AllCL.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// @ Controller + @ResponseBody
@RestController
@RequestMapping("/api/v1/ticketing")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    // 티켓 구매 요청
    @PostMapping
    public ResponseEntity<TicketResponseDto> purchaseTicket(@RequestBody TicketRequestDto requestDto) {
        TicketResponseDto responseDto = ticketService.purchaseTicket(requestDto);

        // HTTP 상태 코드 201 (Created)과 함께 응답 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }


    // 티켓팅 결과 전체 조회
    @GetMapping
    public ResponseEntity<List<TicketResponseDto>>getAllTicket() {
        List<TicketResponseDto> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

}
