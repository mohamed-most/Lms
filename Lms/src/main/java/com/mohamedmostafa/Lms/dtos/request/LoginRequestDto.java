package com.mohamedmostafa.Lms.dtos.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LoginRequestDto {
    private String email;
    private String password;
}
