package com.Cloudwave.Backend_AllCL.service;

import com.Cloudwave.Backend_AllCL.dto.ticket.TicketRequestDto;
import com.Cloudwave.Backend_AllCL.dto.ticket.TicketResponseDto;
// import com.Cloudwave.Backend_AllCL.entity.Ticket;
// import com.Cloudwave.Backend_AllCL.entity.TicketInventory;
// import com.Cloudwave.Backend_AllCL.entity.User;
// import com.Cloudwave.Backend_AllCL.exception.CustomException;
// import com.Cloudwave.Backend_AllCL.exception.ErrorCode;
// import com.Cloudwave.Backend_AllCL.repository.TicketInventoryRepository;
// import com.Cloudwave.Backend_AllCL.repository.TicketRepository;
// import com.Cloudwave.Backend_AllCL.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    // 주석: RDS 관련 Repository는 사용하지 않음
    // private final TicketRepository ticketRepository;
    // private final UserRepository userRepository;
    // private final TicketInventoryRepository ticketInventoryRepository;

    private final AmazonSQS sqsClient = AmazonSQSClientBuilder.defaultClient();
    private final String queueUrl = "https://sqs.ap-northeast-2.amazonaws.com/961341508965/ticketing-queue.fifo";

    @Transactional
    public TicketResponseDto purchaseTicket(TicketRequestDto requestDto) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String messageBody = mapper.writeValueAsString(requestDto);

            SendMessageRequest sendMessageRequest = new SendMessageRequest()
                    .withQueueUrl(queueUrl)
                    .withMessageBody(messageBody)
                    .withMessageGroupId("ticketingGroup");

            System.out.println("✅ SQS 메시지 전송 시도: " + messageBody);
            SendMessageResult result = sqsClient.sendMessage(sendMessageRequest);
            System.out.println("✅ SQS 메시지 전송 완료: " + result.toString());

        } catch (Exception e) {
            e.printStackTrace();
            // 주석: 사용자 정의 예외 제거
            // throw new CustomException(ErrorCode.SQS_SEND_FAIL);
            throw new RuntimeException("SQS 메시지 전송 실패");
        }

        // 주석: 실제 티켓 저장 로직 제거
        // return new TicketResponseDto(savedTicket);
        return null; // 또는 필요시 dummy 응답 반환
    }

    public List<TicketResponseDto> getAllTickets() {
        // 주석: DB에서 티켓 조회 제거
        // return ticketRepository.findAll().stream().map(TicketResponseDto::new).toList();
        return Collections.emptyList(); // 빈 리스트 반환
    }
}
