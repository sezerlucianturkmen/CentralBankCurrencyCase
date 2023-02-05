package com.xinerji.exception;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_ERROR(2000, "Internal Server Error", INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(2001, "Invalid Parameter Error", BAD_REQUEST),
    ENTITY_NOT_SAVED(2002, "Invalid Parameter Error", BAD_REQUEST),
    CURRENCY_NOT_EXIST(3000, "There is not any currency for this date", BAD_REQUEST),
    CURRENCY_NOT_CREATED(3001, "Currencies are not saved", BAD_REQUEST),
    DATE_EXIST(3002, "Requested date is already on the table", BAD_REQUEST);



    private int code;
    private String message;
    HttpStatus httpStatus;

}