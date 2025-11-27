package com.mohamedmostafa.Lms.exceptionhandling;

import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import com.mohamedmostafa.Lms.exceptions.ResourceNotFoundEx;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final String message = "Something went wrong !!";


    @ExceptionHandler(ResourceNotFoundEx.class)
    public ResponseEntity<ApiResponse<String>> handleResourceNotFound(ResourceNotFoundEx resourceNotFoundEx) {
        return ApiResponse.error("Bad Request : " + resourceNotFoundEx.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGlobalException(Exception exception) {
        return ApiResponse.error(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
