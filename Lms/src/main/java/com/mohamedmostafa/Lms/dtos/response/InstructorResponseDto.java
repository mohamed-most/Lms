package com.mohamedmostafa.Lms.dtos.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InstructorResponseDto {
    private Integer id;

    private String username;

    private String email;

    private String specialization;

    private String bio;

    private Integer yearsOfExperience;

    private Integer departmentId;
}
