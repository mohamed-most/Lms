package com.mohamedmostafa.Lms.controller;


import com.mohamedmostafa.Lms.dto.request.EnrollmentRequest;
import com.mohamedmostafa.Lms.dto.response.ApiResponse;
import com.mohamedmostafa.Lms.dto.response.EnrollmentResponse;
import com.mohamedmostafa.Lms.entity.Enrollment;
import com.mohamedmostafa.Lms.mapper.EnrollmentsMapper;
import com.mohamedmostafa.Lms.service.EnrollmentService;
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

    private final EnrollmentService enrollmentService;
    private final EnrollmentsMapper enrollmentsMapper;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService, EnrollmentsMapper enrollmentsMapper) {
        this.enrollmentService = enrollmentService;
        this.enrollmentsMapper = enrollmentsMapper;
    }

    @PostMapping({"/", ""})
    public ResponseEntity<ApiResponse<EnrollmentResponse>> enrollstudnet(@Valid @RequestBody EnrollmentRequest enrollmentRequest) {
        Enrollment enrollment = enrollmentService.enrollStudent(enrollmentRequest.getStudentId(), enrollmentRequest.getCourseId());
        EnrollmentResponse enrollmentResponse = enrollmentsMapper.toEnrollmentsResponse(enrollment);
        return ApiResponse.success(enrollmentResponse, HttpStatus.CREATED);
    }
}
