package com.mohamedmostafa.Lms.services.abstracts;

import com.mohamedmostafa.Lms.dtos.response.StudentResponseDto;

public interface StudentService {


    // Find a student by ID
    public StudentResponseDto getStudent(Integer studentId);


}
