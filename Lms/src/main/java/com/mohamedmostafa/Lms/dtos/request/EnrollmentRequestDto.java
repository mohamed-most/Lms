package com.mohamedmostafa.Lms.dtos.request;

import com.mohamedmostafa.Lms.enums.CourseStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class EnrollmentRequestDto {

    @NotNull
    Integer studentId;

    @NotNull
    Integer courseId;


    CourseStatus status; // optional, default ACTIVE or DROPPED
}
