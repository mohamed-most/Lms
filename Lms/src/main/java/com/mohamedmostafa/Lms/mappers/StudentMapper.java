package com.mohamedmostafa.Lms.mappers;

import com.mohamedmostafa.Lms.dtos.response.StudentResponseDto;
import com.mohamedmostafa.Lms.entity.Student;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    private final PasswordEncoder passwordEncoder;

    public StudentMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public static StudentResponseDto toResponseDto(Student student) {
        return StudentResponseDto.builder()
                .username(student.getUsername())
                .email(student.getEmail())
                .build();
    }

 
}
