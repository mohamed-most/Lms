package com.mohamedmostafa.Lms.mappers;

import com.mohamedmostafa.Lms.dtos.request.StudentSignUpRequestDto;
import com.mohamedmostafa.Lms.dtos.response.StudentResponseDto;
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

    public static StudentResponseDto toResponseDto(Student student) {
        return StudentResponseDto.builder()
                .username(student.getUsername())
                .email(student.getEmail())
                .build();
    }

    public Student toEntityForSignup(StudentSignUpRequestDto dto) {
        return Student.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.STUDENT)
                .build();
    }
}
