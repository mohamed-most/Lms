package com.mohamedmostafa.Lms.dtos.request;

import lombok.Value;

@Value
public class InstructorRequestDto {
    String username;

    String email;

    String password;

    String specialization;

    String bio;

    Integer yearsOfExperience;

    Integer departmentId; // foreign key reference
}
