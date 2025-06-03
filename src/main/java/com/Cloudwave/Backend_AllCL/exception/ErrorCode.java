package com.Cloudwave.Backend_AllCL.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    TICKET_NOT_FOUND(HttpStatus.NOT_FOUND, "티켓이 존재하지 않습니다."),
    TICKET_OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "티켓이 품절되었습니다."),
    DUPLICATE_PURCHASE(HttpStatus.CONFLICT, "이미 티켓을 구매하셨습니다."),

    SQS_SEND_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "SQS 메시지 전송에 실패했습니다.");
    private final HttpStatus status;
    private final String message;
}
