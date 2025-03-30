package com.Cloudwave.Backend_AllCL.entity;

import com.Cloudwave.Backend_AllCL.exception.CustomException;
import com.Cloudwave.Backend_AllCL.exception.ErrorCode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Getter
public class TicketInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    // 초기 재고 설정
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
