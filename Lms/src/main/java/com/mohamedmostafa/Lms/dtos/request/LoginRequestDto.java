package com.mohamedmostafa.Lms.dtos.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
@Setter
@Getter
@Builder
public class LoginRequestDto {
    String email;
    String password;
}
