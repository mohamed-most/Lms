package com.mohamedmostafa.Lms.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class StudentSignUpDto {
    private String username;
    private String email;
    private String password;
}
