package com.mohamedmostafa.Lms.admin.departments;


import com.mohamedmostafa.Lms.dtos.request.DepartmentRequestDto;
import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import com.mohamedmostafa.Lms.dtos.response.DepartmentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/department")
public class AdminDepartmentController {

    private final AdminDepartmentService adminDepartmentService;

    @Autowired
    public AdminDepartmentController(AdminDepartmentService adminDepartmentService) {
        this.adminDepartmentService = adminDepartmentService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<DepartmentResponseDto>>> getAllDepartments() {
        return ApiResponse.success(adminDepartmentService.getAllDepartments(), HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<ApiResponse<DepartmentResponseDto>> createDepartment(@RequestBody DepartmentRequestDto departmentRequestDto) {
        return ApiResponse.success(adminDepartmentService.createDepartment(departmentRequestDto), HttpStatus.OK);
    }
}
