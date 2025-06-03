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

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketInventoryRepository ticketInventoryRepository;

    private final AmazonSQS sqsClient = AmazonSQSClientBuilder.defaultClient();
    private final String queueUrl = "https://sqs.ap-northeast-2.amazonaws.com/961341508965/ticketing-queue.fifo";

    @Transactional
    public TicketResponseDto purchaseTicket(TicketRequestDto requestDto){

        // 사용자 조회
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 중복 티켓팅 구매 확인
        boolean alreadyPurchased = ticketRepository.existsByUserEmail(requestDto.getEmail());
        if(alreadyPurchased){
            throw new CustomException(ErrorCode.DUPLICATE_PURCHASE);
        }

        // 티켓 이름 요청에서 받기
        TicketInventory inventory = ticketInventoryRepository.findByName(requestDto.getTicketName())
                .orElseThrow(() -> new CustomException(ErrorCode.TICKET_NOT_FOUND));

        // 재고 감소
        inventory.decreaseStock();

	//SQS 
	try {
	    ObjectMapper mapper = new ObjectMapper();
	    String messageBody = mapper.writeValueAsString(requestDto);

	    SendMessageRequest sendMessageRequest = new SendMessageRequest()
		     .withQueueUrl(queueUrl)
		     .withMessageBody(messageBody)
		     .withMessageGroupId("ticketingGroup"); //FIFO
	    System.out.println("✅ SQS 메시지 전송 시도: " + messageBody);
	    sqsClient.sendMessage(sendMessageRequest);
	} catch (Exception e) {
		e.printStackTrace();
		throw new CustomException(ErrorCode.SQS_SEND_FAIL);
	}
        // 티켓 저장
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
