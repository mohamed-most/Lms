package com.mohamedmostafa.Lms.exception_handling;

import com.mohamedmostafa.Lms.dto.response.ApiResponse;
import com.mohamedmostafa.Lms.errors.ResourceNotFoundEx;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundEx.class)
    public ResponseEntity<ApiResponse<?>> handleStudentNotFound(ResourceNotFoundEx resourceNotFoundEx) {
        ApiResponse<?> response = ApiResponse.builder().success(false)
                .error("Bad Request : " + resourceNotFoundEx.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGlobalException(Exception exception) {
        ApiResponse<?> response = ApiResponse.builder().success(false)
                .error(exception.getMessage()).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
