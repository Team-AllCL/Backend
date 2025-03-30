package com.Cloudwave.Backend_AllCL.dto.ticket;

import com.Cloudwave.Backend_AllCL.entity.Ticket;
import lombok.Getter;


@Getter
public class TicketResponseDto {
    private String name;
    private Long userId;

    public TicketResponseDto(Ticket ticket) {
        this.name = ticket.getTicketInventory().getName();
        this.userId = ticket.getUser().getId();
    }
}
