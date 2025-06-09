package com.Cloudwave.Backend_AllCL.dto.ticket;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true) 
@Getter
public class TicketRequestDto {

    private String email;  // userId -> email 변경
    private String ticketName; // 티켓이름 : 올리브영 페스타

    public TicketRequestDto(String email, String ticketName) {
        this.email = email;
        this.ticketName = ticketName;
    }
}
