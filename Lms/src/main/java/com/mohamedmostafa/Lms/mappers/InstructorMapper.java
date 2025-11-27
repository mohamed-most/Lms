package com.mohamedmostafa.Lms.mappers;

import com.mohamedmostafa.Lms.dtos.request.InstructorRequestDto;
import com.mohamedmostafa.Lms.dtos.response.InstructorResponseDto;
import com.mohamedmostafa.Lms.entity.Department;
import com.mohamedmostafa.Lms.entity.Instructor;
import com.mohamedmostafa.Lms.repositories.DepartmentRepo;

public class InstructorMapper {

    public static InstructorResponseDto toResponseDto(Instructor instructor) {

        return InstructorResponseDto.builder()
                .id(instructor.getId())
                .username(instructor.getUsername())
                .email(instructor.getEmail())
                .specialization(instructor.getSpecialization())
                .bio(instructor.getBio())
                .yearsOfExperience(instructor.getYearsOfExperience())
                .departmentId(instructor.getDepartment() != null ?
                        instructor.getDepartment().getId() : null)
                .build();
    }

    public static Instructor toInstructor(InstructorRequestDto dto, DepartmentRepo departmentRepo) {
        Department department = null;
        if (dto.getDepartmentId() != null) {
            department = departmentRepo.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
        }

        return Instructor.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword()) // make sure to encode
                .specialization(dto.getSpecialization())
                .bio(dto.getBio())
                .yearsOfExperience(dto.getYearsOfExperience())
                .department(department)
                .build();
    }


}
