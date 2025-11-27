package com.mohamedmostafa.Lms.dtos.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class StudentResponseDto {
    private String username;
    private String email;
}