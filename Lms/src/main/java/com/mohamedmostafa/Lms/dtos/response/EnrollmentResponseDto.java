package com.mohamedmostafa.Lms.dtos.response;

import com.mohamedmostafa.Lms.enums.CourseStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class EnrollmentResponseDto {

    private StudentResponseDto student;
    private CourseResponseDto course;
    private LocalDateTime createdAt;
    private CourseStatus status;
}
