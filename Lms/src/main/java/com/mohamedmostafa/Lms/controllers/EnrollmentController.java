package com.mohamedmostafa.Lms.controllers;


import com.mohamedmostafa.Lms.dtos.request.EnrollmentRequestDto;
import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import com.mohamedmostafa.Lms.dtos.response.EnrollmentResponseDto;
import com.mohamedmostafa.Lms.services.abstracts.EnrollmentService;
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


    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping({"/", ""})
    public ResponseEntity<ApiResponse<EnrollmentResponseDto>> enrolStudentInCourse(@RequestBody EnrollmentRequestDto enrollmentRequest) {

        return ApiResponse.success(enrollmentService.enrolStudentInCourse(enrollmentRequest), HttpStatus.CREATED);
    }

    @PostMapping("/status")
    public ResponseEntity<ApiResponse<EnrollmentResponseDto>> changeCourseStatus(@RequestBody EnrollmentRequestDto enrollmentRequest) {

        return ApiResponse.success(enrollmentService.changeCourseStatus(enrollmentRequest), HttpStatus.OK);
    }
}
