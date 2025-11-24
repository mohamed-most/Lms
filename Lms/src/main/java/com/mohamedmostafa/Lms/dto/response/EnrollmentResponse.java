package com.mohamedmostafa.Lms.dto.response;

import com.mohamedmostafa.Lms.enums.CourseStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class EnrollmentResponse {

    private Integer enrollmentId;
    private StudentResponseDto student;
    private CourseResponseDto course;
    private LocalDateTime createdAt;
    private CourseStatus status;
}
