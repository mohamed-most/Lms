package com.mohamedmostafa.Lms.admin.instructors;


import com.mohamedmostafa.Lms.dtos.request.InstructorRequestDto;
import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import com.mohamedmostafa.Lms.dtos.response.InstructorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/instructors")
public class AdminInstructorComponent {


    private final AdminInstructorService adminInstructorService;

    public AdminInstructorComponent(AdminInstructorService adminInstructorService) {
        this.adminInstructorService = adminInstructorService;
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<InstructorResponseDto>> createInstructor(
            @RequestBody InstructorRequestDto instructorRequestDto) {
        return ApiResponse.success(adminInstructorService.createInstructor(instructorRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<InstructorResponseDto>>> getAllInstructors() {
        return ApiResponse.success(adminInstructorService.getAllInstructors(), HttpStatus.OK);
    }
}
