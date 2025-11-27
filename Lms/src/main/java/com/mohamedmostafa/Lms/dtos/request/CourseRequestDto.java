package com.mohamedmostafa.Lms.dtos.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;


@Value
public class CourseRequestDto {


    @NotBlank(message = "course code is required ")
    @Size(min = 3, max = 10, message = "course code length between 3 and 10 ")
    String courseCode;


    @NotBlank(message = "course name is required ")
    String courseName;
}
