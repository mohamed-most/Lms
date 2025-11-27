package com.mohamedmostafa.Lms.admin.students;

import com.mohamedmostafa.Lms.dtos.request.StudentSignUpRequestDto;
import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import com.mohamedmostafa.Lms.dtos.response.StudentResponseDto;
import com.mohamedmostafa.Lms.repositories.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@PreAuthorize("hasRole('ADMIN')") // expects role to be ROLE_ADMIN internally
@RestController
@RequestMapping("/api/v1/admin/student")
public class AdminStudentController {
    private final AdminStudentService adminStudentService;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    public AdminStudentController(AdminStudentService adminStudentService) {
        this.adminStudentService = adminStudentService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getStudent(Integer id) {
        return ApiResponse.success(adminStudentService.getStudent(id), HttpStatus.OK);
    }

    @GetMapping({"/ ", ""})
    public ResponseEntity<ApiResponse<List<StudentResponseDto>>> getAllStudents() {
        return ApiResponse.success(adminStudentService.getAllStudents(), HttpStatus.OK);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<ApiResponse<StudentResponseDto>> createStudent(@RequestBody StudentSignUpRequestDto studentSignUpRequestDto) {
        return ApiResponse.success(adminStudentService.createStudent(studentSignUpRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "studentId") Integer studentId) {
        adminStudentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }


}
