package com.mohamedmostafa.Lms.controller;


import com.mohamedmostafa.Lms.dto.request.StudentLoginDto;
import com.mohamedmostafa.Lms.dto.request.StudentSignUpDto;
import com.mohamedmostafa.Lms.dto.response.ApiResponse;
import com.mohamedmostafa.Lms.dto.response.StudentResponseDto;
import com.mohamedmostafa.Lms.entity.Student;
import com.mohamedmostafa.Lms.mapper.StudentMapper;
import com.mohamedmostafa.Lms.service.AuthService;
import com.mohamedmostafa.Lms.service.StudentService;
import com.mohamedmostafa.Lms.util.JwtUtil;
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
    private final StudentMapper studentMapper;
    private final StudentService studentService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthContoller(AuthService authService, StudentService studentService, StudentMapper studentMapper) {
        this.authService = authService;
        this.studentService = studentService;
        this.studentMapper = studentMapper;

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, Object>>> login(@RequestBody StudentLoginDto loginDto) {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );
            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);

            Map<String, Object> data = new HashMap<>();
            data.put("token", token);
            data.put("email", userDetails.getUsername());
            data.put("role", userDetails.getAuthorities());


            return ApiResponse.success(data, HttpStatus.OK);

        } catch (Exception e) {
            return ApiResponse.error("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<StudentResponseDto>> signup(@RequestBody StudentSignUpDto signUpDto) {

        // 1 Map DTO → Entity
        Student student = studentMapper.toEntityForSignup(signUpDto);

        // 2 Save Student to DB
        Student savedStudent = studentService.createStudent(student);

        // 3 Map Entity → Response DTO
        StudentResponseDto responseDto = studentMapper.toResponseDto(savedStudent);

        // 4 Return Success Response
        return ApiResponse.success(responseDto, HttpStatus.CREATED);
    }

}
