package com.mohamedmostafa.Lms.admin;


import com.mohamedmostafa.Lms.dtos.request.DepartmentRequestDto;
import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import com.mohamedmostafa.Lms.dtos.response.DepartmentResponseDto;
import com.mohamedmostafa.Lms.services.abstracts.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/department")
public class AdminDepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public AdminDepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<DepartmentResponseDto>>> getAllDepartments() {
        return ApiResponse.success(departmentService.getAllDepartments(), HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<ApiResponse<DepartmentResponseDto>> createDepartment(@RequestBody DepartmentRequestDto departmentRequestDto) {
        return ApiResponse.success(departmentService.createDepartment(departmentRequestDto), HttpStatus.OK);
    }
}
