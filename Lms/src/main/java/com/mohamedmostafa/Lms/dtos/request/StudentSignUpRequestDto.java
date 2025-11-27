package com.mohamedmostafa.Lms.dtos.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class StudentSignUpRequestDto {
    private String username;
    private String email;
    private String password;
}
