package com.Cloudwave.Backend_AllCL.dto.ticket;

import lombok.Getter;

@Getter
public class TicketRequestDto {

    private Long userId;
    private String ticketName; // 티켓이름 : 올리브영 페스타

    public TicketRequestDto(Long userId, String ticketName) {
        this.userId = userId;
        this.ticketName = ticketName;
    }
}
