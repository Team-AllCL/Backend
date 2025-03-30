package com.Cloudwave.Backend_AllCL.entity;

import com.Cloudwave.Backend_AllCL.exception.CustomException;
import com.Cloudwave.Backend_AllCL.exception.ErrorCode;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Getter
@Table(name = "ticket_inventory")
public class TicketInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    private int stock;

    // 티켓 감소
    @Transactional
    public void decreaseStock() {
        if(stock <= 0){
            throw new CustomException(ErrorCode.TICKET_OUT_OF_STOCK);
        }
        this.stock -= 1;
    }
}
