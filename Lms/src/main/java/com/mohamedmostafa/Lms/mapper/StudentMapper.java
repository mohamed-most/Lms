package com.mohamedmostafa.Lms.mapper;

import com.mohamedmostafa.Lms.dto.request.StudentSignUpDto;
import com.mohamedmostafa.Lms.dto.response.StudentResponseDto;
import com.mohamedmostafa.Lms.entity.Student;
import com.mohamedmostafa.Lms.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    private final PasswordEncoder passwordEncoder;

    public StudentMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Student toEntityForSignup(StudentSignUpDto dto) {
        return Student.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.STUDENT)
                .build();
    }

    public StudentResponseDto toResponseDto(Student student) {
        return StudentResponseDto.builder()
                .username(student.getUsername())
                .email(student.getEmail())
                .build();
    }
}
