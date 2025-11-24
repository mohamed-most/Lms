package com.mohamedmostafa.Lms.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
public class CourseRequestDto {


    @NotBlank(message = "course code is required ")
    @Size(min = 3, max = 10, message = "course code length between 3 and 10 ")
    private String courseCode;

    
    @NotBlank(message = "course name is required ")
    private String courseName;
}
