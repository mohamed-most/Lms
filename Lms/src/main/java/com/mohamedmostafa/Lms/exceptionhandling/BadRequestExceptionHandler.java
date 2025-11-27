package com.mohamedmostafa.Lms.exceptionhandling;


import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import com.mohamedmostafa.Lms.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.mohamedmostafa.Lms.messages.ExceptionHandlersMessages.BAD_REQUEST_MSG;

@ControllerAdvice
public class BadRequestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<String>> badRequestHandler(BadRequestException badRequestException) {
        return ApiResponse.error(
                badRequestException.getMessage() != null ? badRequestException.getMessage() : BAD_REQUEST_MSG,
                HttpStatus.BAD_REQUEST);
    }
}
