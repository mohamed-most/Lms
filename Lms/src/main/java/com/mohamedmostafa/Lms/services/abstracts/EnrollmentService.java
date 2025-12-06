package com.mohamedmostafa.Lms.services.abstracts;

import com.mohamedmostafa.Lms.dtos.request.EnrollmentRequestDto;
import com.mohamedmostafa.Lms.dtos.response.EnrollmentResponseDto;

public interface EnrollmentService {

    public EnrollmentResponseDto enrolStudentInCourse(EnrollmentRequestDto enrollmentRequest);


    public EnrollmentResponseDto changeCourseStatus(EnrollmentRequestDto enrollmentRequest);

}
