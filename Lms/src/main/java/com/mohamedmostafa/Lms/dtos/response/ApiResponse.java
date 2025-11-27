package com.mohamedmostafa.Lms.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private String error;
    private Object meta; // Optional metadata

    // Success response with custom status
    public static <T> ResponseEntity<ApiResponse<T>> success(T data, HttpStatus status) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .build();
        return new ResponseEntity<>(response, status);
    }

    // Success with metadata and custom status
    public static <T> ResponseEntity<ApiResponse<T>> success(T data, Object meta, HttpStatus status) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .meta(meta)
                .build();
        return new ResponseEntity<>(response, status);
    }

    // Error response with custom status
    public static <T> ResponseEntity<ApiResponse<T>> error(String errorMessage, HttpStatus status) {
        ApiResponse<T> response = ApiResponse.<T>builder()
                .success(false)
                .error(errorMessage)
                .build();
        return new ResponseEntity<>(response, status);
    }
}
