package com.Cloudwave.Backend_AllCL.service;

import com.Cloudwave.Backend_AllCL.dto.ticket.TicketRequestDto;
import com.Cloudwave.Backend_AllCL.dto.ticket.TicketResponseDto;
import com.Cloudwave.Backend_AllCL.entity.Ticket;
import com.Cloudwave.Backend_AllCL.entity.TicketInventory;
import com.Cloudwave.Backend_AllCL.entity.User;
import com.Cloudwave.Backend_AllCL.exception.CustomException;
import com.Cloudwave.Backend_AllCL.exception.ErrorCode;
import com.Cloudwave.Backend_AllCL.repository.TicketInventoryRepository;
import com.Cloudwave.Backend_AllCL.repository.TicketRepository;
import com.Cloudwave.Backend_AllCL.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketInventoryRepository ticketInventoryRepository;


    @Transactional
    public TicketResponseDto purchaseTicket(TicketRequestDto requestDto){
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        boolean alreadyPurchased = ticketRepository.existsByUser(user);
        if(alreadyPurchased){
            throw new CustomException(ErrorCode.DUPLICATE_PURCHASE);
        }

        // inventoryId로 조회
        TicketInventory inventory = ticketInventoryRepository.findById(requestDto.getInventoryId())
                .orElseThrow(() -> new CustomException(ErrorCode.TICKET_NOT_FOUND));

        inventory.decreaseStock();

        Ticket ticket = Ticket.builder()
                .user(user)
                .ticketInventory(inventory)
                .build();

        Ticket savedTicket = ticketRepository.save(ticket);

        return new TicketResponseDto(savedTicket);
    }


    public List<TicketResponseDto> getAllTickets(){
        return ticketRepository.findAll().stream()
                .map(TicketResponseDto::new)
                .toList();
    }

}
