package com.mohamedmostafa.Lms.controllers;


import com.mohamedmostafa.Lms.dtos.request.LoginRequestDto;
import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import com.mohamedmostafa.Lms.mappers.StudentMapper;
import com.mohamedmostafa.Lms.services.abstracts.AuthService;
import com.mohamedmostafa.Lms.services.concretes.StudentServiceImp;
import com.mohamedmostafa.Lms.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthContoller {

    private final AuthService authService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthContoller(AuthService authServiceImp, StudentServiceImp studentServiceImp, StudentMapper studentMapper) {
        this.authService = authServiceImp;


    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, Object>>> login(@RequestBody LoginRequestDto loginDto) {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);

            //TODO : need refactor as make Mapper to this
            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("email", userDetails.getUsername());
            data.put("role", userDetails.getAuthorities());


            return ApiResponse.success(data, HttpStatus.OK);

        } catch (Exception e) {
            return ApiResponse.error("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }

//
//    @PostMapping("/signup")
//    public ResponseEntity<ApiResponse<StudentResponseDto>> signup(@RequestBody StudentSignUpRequestDto signUpDto) {
//
//        // 1 Map DTO → Entity
//        Student student = studentMapper.toEntityForSignup(signUpDto);
//
//        // 2 Save Student to DB
//        Student savedStudent = studentServiceImp.createStudent(StudentMapper student);
//
//        // 3 Map Entity → Response DTO
//        StudentResponseDto responseDto = studentMapper.toResponseDto(savedStudent);
//
//        // 4 Return Success Response
//        return ApiResponse.success(responseDto, HttpStatus.CREATED);
//    }

}
