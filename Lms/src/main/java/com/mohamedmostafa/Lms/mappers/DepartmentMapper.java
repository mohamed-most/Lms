package com.mohamedmostafa.Lms.mappers;

import com.mohamedmostafa.Lms.dtos.response.DepartmentResponseDto;
import com.mohamedmostafa.Lms.dtos.response.InstructorResponseDto;
import com.mohamedmostafa.Lms.entity.Department;

import java.util.List;

public class DepartmentMapper {
    public static DepartmentResponseDto toResponseDto(Department department) {
        List<InstructorResponseDto> instructorDtos = department.getInstructors() == null
                ? null
                : department.getInstructors().stream()
                .map(InstructorMapper::toResponseDto)
                .toList();

        return DepartmentResponseDto.builder()
                .id(department.getId())
                .departmentName(department.getDepartment_name())
                .departmentCode(department.getDepartment_code())
                .instructors(instructorDtos)
                .build();
    }


}
