package com.mohamedmostafa.Lms.services.abstracts;

import com.mohamedmostafa.Lms.dtos.request.LoginRequestDto;
import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface AuthService {


    public ResponseEntity<ApiResponse<Map<String, Object>>> login(@RequestBody LoginRequestDto loginDto);


    // only student can sign up
    //    public ResponseEntity<ApiResponse<StudentResponseDto>> signup(@RequestBody StudentSignUpRequestDto signUpDto);
}
