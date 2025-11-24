package com.mohamedmostafa.Lms.dto.request;

import com.mohamedmostafa.Lms.enums.CourseStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnrollmentRequest {

    @NotNull
    private Integer studentId;

    @NotNull
    private Integer courseId;


    private CourseStatus status; // optional, default ACTIVE or DROPPED
}
