package com.mohamedmostafa.Lms.controller;


import com.mohamedmostafa.Lms.dto.request.StudentSignUpDto;
import com.mohamedmostafa.Lms.dto.response.ApiResponse;
import com.mohamedmostafa.Lms.dto.response.StudentResponseDto;
import com.mohamedmostafa.Lms.entity.Student;
import com.mohamedmostafa.Lms.mapper.StudentMapper;
import com.mohamedmostafa.Lms.security.UserDetailsImpl;
import com.mohamedmostafa.Lms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentController(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }


    @GetMapping("/{studentId}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getStudent(@PathVariable Integer studentId,
                                                                      @AuthenticationPrincipal UserDetailsImpl loggedUser) {


        Student student = studentService.findStudentByEamil(loggedUser.getEmail());
        if (!Objects.equals(student.getId(), studentId))
            throw new AccessDeniedException("this is user can't get this id");


        // Map entity to DTO
        StudentResponseDto studentResponseDto = studentMapper.toResponseDto(student);

        // Build consistent API response
        return ApiResponse.success(studentResponseDto, HttpStatus.OK);
    }


    @PostMapping({"/", ""})
    public ResponseEntity<ApiResponse<StudentResponseDto>> createStudent(@RequestBody StudentSignUpDto signUpDto) {

        // Map DTO to entity
        Student student = studentMapper.toEntityForSignup(signUpDto);

        // Hash password future req.
        student.setPassword(signUpDto.getPassword()); // ideally use passwordEncoder here

        // Save student
        Student savedStudent = studentService.createStudent(student);

        // Map entity to response DTO
        StudentResponseDto responseDto = studentMapper.toResponseDto(savedStudent);

        // Return success with 201 Created
        return ApiResponse.success(responseDto, HttpStatus.CREATED);
    }


}
