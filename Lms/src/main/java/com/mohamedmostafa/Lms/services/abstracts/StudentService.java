package com.mohamedmostafa.Lms.services.abstracts;

import com.mohamedmostafa.Lms.dtos.request.StudentSignUpRequestDto;
import com.mohamedmostafa.Lms.dtos.response.StudentResponseDto;

import java.util.List;

public interface StudentService {


    public StudentResponseDto getStudent(Integer studentId);


    public StudentResponseDto createStudent(StudentSignUpRequestDto studentSignUpRequestDto);


    public void deleteStudent(Integer id);

    // Get all students
    public List<StudentResponseDto> getAllStudents();
}
