package com.mohamedmostafa.Lms.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class CourseResponseDto {
    private String courseCode;
    private String courseName;
}
