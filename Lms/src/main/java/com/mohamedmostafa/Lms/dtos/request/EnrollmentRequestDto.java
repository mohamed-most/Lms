package com.mohamedmostafa.Lms.dtos.request;

import com.mohamedmostafa.Lms.enums.CourseStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

@Value
@Data
public class EnrollmentRequestDto {

    @NotNull
    Integer courseId;


    CourseStatus status; // optional, default ACTIVE or DROPPED
}
