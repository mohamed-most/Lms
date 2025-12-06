package com.mohamedmostafa.Lms.services.abstracts;

import com.mohamedmostafa.Lms.dtos.request.DepartmentRequestDto;
import com.mohamedmostafa.Lms.dtos.response.DepartmentResponseDto;

import java.util.List;

public interface DepartmentService {

    public List<DepartmentResponseDto> getAllDepartments();

    public DepartmentResponseDto createDepartment(DepartmentRequestDto dto);
}
