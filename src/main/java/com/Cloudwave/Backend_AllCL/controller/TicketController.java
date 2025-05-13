package com.Cloudwave.Backend_AllCL.controller;

import com.Cloudwave.Backend_AllCL.dto.ticket.TicketRequestDto;
import com.Cloudwave.Backend_AllCL.dto.ticket.TicketResponseDto;
import com.Cloudwave.Backend_AllCL.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://dkgmp1k5bgam1.cloudfront.net")
@CrossOrigin(origins = "http://allcle-order.click")

@RestController
@RequestMapping("/api/v1/ticketing")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponseDto> createTicket(@RequestBody TicketRequestDto ticketRequestDto) {
        System.out.println("[티켓 발급 요청] 사용자 이메일: " + ticketRequestDto.getEmail() +
                           ", 티켓 이름: " + ticketRequestDto.getTicketName());

        try {
            TicketResponseDto responseDto = ticketService.purchaseTicket(ticketRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDto>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
}
