package com.mohamedmostafa.Lms.dtos.request;

import com.mohamedmostafa.Lms.enums.CourseStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollmentRequestDto {

    @NotNull
    private Integer studentId;

    @NotNull
    private Integer courseId;


    private CourseStatus status; // optional, default ACTIVE or DROPPED
}
