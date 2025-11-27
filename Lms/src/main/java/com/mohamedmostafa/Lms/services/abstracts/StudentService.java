package com.mohamedmostafa.Lms.services.abstracts;

import com.mohamedmostafa.Lms.dtos.response.StudentResponseDto;
import com.mohamedmostafa.Lms.entity.Student;
import com.mohamedmostafa.Lms.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface StudentService {


    // Find a student by ID
    public StudentResponseDto getStudent(Integer studentId, @AuthenticationPrincipal UserDetailsImpl loggedUser);

    // Get all students
    public List<StudentResponseDto> getAllStudents();

    public StudentResponseDto createStudent(Student student);


    // Delete a student
    public void deleteStudent(Integer id);


}
