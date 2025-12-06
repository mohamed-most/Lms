package com.mohamedmostafa.Lms.mappers;

import com.mohamedmostafa.Lms.dtos.response.DepartmentResponseDto;
import com.mohamedmostafa.Lms.entity.Department;

public class DepartmentMapper {
    public static DepartmentResponseDto toResponseDto(Department department) {
        return DepartmentResponseDto.builder()
                .departmentCode(department.getDepartment_code())
                .departmentName(department.getDepartment_name())
                .build();
    }

}
