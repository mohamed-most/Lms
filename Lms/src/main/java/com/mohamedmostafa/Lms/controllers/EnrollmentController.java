package com.mohamedmostafa.Lms.controllers;


import com.mohamedmostafa.Lms.dtos.request.EnrollmentRequestDto;
import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import com.mohamedmostafa.Lms.dtos.response.EnrollmentResponse;
import com.mohamedmostafa.Lms.entity.Enrollment;
import com.mohamedmostafa.Lms.mappers.EnrollmentsMapper;
import com.mohamedmostafa.Lms.services.concretes.EnrollmentServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

    private final EnrollmentServiceImp enrollmentServiceImp;
    private final EnrollmentsMapper enrollmentsMapper;

    @Autowired
    public EnrollmentController(EnrollmentServiceImp enrollmentServiceImp, EnrollmentsMapper enrollmentsMapper) {
        this.enrollmentServiceImp = enrollmentServiceImp;
        this.enrollmentsMapper = enrollmentsMapper;
    }

    @PostMapping({"/", ""})
    public ResponseEntity<ApiResponse<EnrollmentResponse>> enrollstudnet(@Valid @RequestBody EnrollmentRequestDto enrollmentRequestDto) {
        Enrollment enrollment = enrollmentServiceImp.enrollStudent(enrollmentRequestDto.getStudentId(), enrollmentRequestDto.getCourseId());
        EnrollmentResponse enrollmentResponse = enrollmentsMapper.toEnrollmentsResponse(enrollment);
        return ApiResponse.success(enrollmentResponse, HttpStatus.CREATED);
    }
}
