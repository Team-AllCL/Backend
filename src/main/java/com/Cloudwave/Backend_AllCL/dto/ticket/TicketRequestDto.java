package com.Cloudwave.Backend_AllCL.dto.ticket;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TicketRequestDto {

    private Long userId;
    private Long inventoryId; // <- 기존 ticketName 대신 inventoryId

    public TicketRequestDto(Long userId, Long inventoryId) {
        this.userId = userId;
        this.inventoryId = inventoryId;
    }
}

