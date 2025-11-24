package com.mohamedmostafa.Lms.exception_handling;

import com.mohamedmostafa.Lms.dto.response.ApiResponse;
import com.mohamedmostafa.Lms.errors.ResourceNotFoundEx;
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
        return ApiResponse.error(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
