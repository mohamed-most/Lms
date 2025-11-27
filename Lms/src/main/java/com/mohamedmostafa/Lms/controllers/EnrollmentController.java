package com.mohamedmostafa.Lms.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {

//    private final EnrollmentServiceImp enrollmentServiceImp;
//    private final EnrollmentsMapper enrollmentsMapper;
//
//    @Autowired
//    public EnrollmentController(EnrollmentServiceImp enrollmentServiceImp, EnrollmentsMapper enrollmentsMapper) {
//        this.enrollmentServiceImp = enrollmentServiceImp;
//        this.enrollmentsMapper = enrollmentsMapper;
//    }
//
//    @PostMapping({"/", ""})
//    public ResponseEntity<ApiResponse<EnrollmentResponse>> enrollstudnet(@Valid @RequestBody EnrollmentRequestDto enrollmentRequestDto) {
//        Enrollment enrollment = enrollmentServiceImp.enrollStudent(enrollmentRequestDto.getStudentId(), enrollmentRequestDto.getCourseId());
//        EnrollmentResponse enrollmentResponse = enrollmentsMapper.toEnrollmentsResponse(enrollment);
//        return ApiResponse.success(enrollmentResponse, HttpStatus.CREATED);
//    }
}
