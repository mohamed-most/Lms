package com.mohamedmostafa.Lms.dto.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
public class CourseRequestDto {
    private String courseCode;
    private String courseName;
}
