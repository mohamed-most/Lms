package com.mohamedmostafa.Lms.admin.departments;


import com.mohamedmostafa.Lms.dtos.request.DepartmentRequestDto;
import com.mohamedmostafa.Lms.dtos.response.DepartmentResponseDto;
import com.mohamedmostafa.Lms.entity.Department;
import com.mohamedmostafa.Lms.exceptions.ResourceNotFoundEx;
import com.mohamedmostafa.Lms.mappers.DepartmentMapper;
import com.mohamedmostafa.Lms.repositories.DepartmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mohamedmostafa.Lms.mappers.DepartmentMapper.toResponseDto;

@Service
public class AdminDepartmentService {

    private final DepartmentRepo departmentRepo;

    public AdminDepartmentService(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    public List<DepartmentResponseDto> getAllDepartments() {
        List<Department> departments = departmentRepo.findAll();

        if (departments.isEmpty()) {
            throw new ResourceNotFoundEx("No departments found");
        }

        return departments.stream()
                .map(DepartmentMapper::toResponseDto)
                .toList();
    }


    public DepartmentResponseDto createDepartment(DepartmentRequestDto dto) {


        Department department = Department.builder()
                .department_name(dto.getDepartmentName())
                .department_code(dto.getDepartmentCode())
                .build();


        return toResponseDto(departmentRepo.save(department));

    }

}
