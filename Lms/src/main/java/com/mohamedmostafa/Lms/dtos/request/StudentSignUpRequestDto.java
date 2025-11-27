package com.mohamedmostafa.Lms.dtos.request;

import lombok.Value;

@Value
public class StudentSignUpRequestDto {
    String username;
    String email;
    String password;
}
