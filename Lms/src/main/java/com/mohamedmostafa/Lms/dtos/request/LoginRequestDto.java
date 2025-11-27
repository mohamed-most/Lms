package com.mohamedmostafa.Lms.dtos.request;

import lombok.Value;

@Value
public class LoginRequestDto {
    String email;
    String password;
}
