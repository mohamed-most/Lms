package com.mohamedmostafa.Lms.admin;

import com.mohamedmostafa.Lms.dtos.request.StudentSignUpRequestDto;
import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import com.mohamedmostafa.Lms.dtos.response.StudentResponseDto;
import com.mohamedmostafa.Lms.repositories.AdminRepo;
import com.mohamedmostafa.Lms.services.abstracts.StudentService;
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
    private final StudentService studentService;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    public AdminStudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getStudent(Integer id) {
        return ApiResponse.success(studentService.getStudent(id), HttpStatus.OK);
    }

    @GetMapping({"/ ", ""})
    public ResponseEntity<ApiResponse<List<StudentResponseDto>>> getAllStudents() {
        return ApiResponse.success(studentService.getAllStudents(), HttpStatus.OK);
    }

    @PostMapping({"", "/"})
    public ResponseEntity<ApiResponse<StudentResponseDto>> createStudent(@RequestBody StudentSignUpRequestDto studentSignUpRequestDto) {
        return ApiResponse.success(studentService.createStudent(studentSignUpRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "studentId") Integer studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }


}
