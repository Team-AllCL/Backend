package com.Cloudwave.Backend_AllCL.controller;

import com.Cloudwave.Backend_AllCL.dto.ticket.TicketRequestDto;
import com.Cloudwave.Backend_AllCL.dto.ticket.TicketResponseDto;
import com.Cloudwave.Backend_AllCL.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @ Controller + @ResponseBody
@RestController
@RequestMapping("/api/v1/ticketing")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    // 티켓 구매 요청
    @PostMapping
    @Operation(summary = "티켓 구매 요청")
    public ResponseEntity<TicketResponseDto> purchaseTicket(@RequestBody TicketRequestDto requestDto) {
        TicketResponseDto responseDto = ticketService.purchaseTicket(requestDto);

        // HTTP 상태 코드 201 (Created)과 함께 응답 반환
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
