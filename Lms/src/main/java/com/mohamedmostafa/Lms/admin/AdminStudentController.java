package com.mohamedmostafa.Lms.admin;

import com.mohamedmostafa.Lms.dto.response.ApiResponse;
import com.mohamedmostafa.Lms.entity.Student;
import com.mohamedmostafa.Lms.errors.ResourceNotFoundEx;
import com.mohamedmostafa.Lms.mapper.StudentMapper;
import com.mohamedmostafa.Lms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/admin/student")
public class AdminStudentController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;


    @Autowired
    public AdminStudentController(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"", "/"})
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents() {
        List<Student> allStudents = studentService.getAllStudents();
        if (allStudents.isEmpty()) throw new ResourceNotFoundEx("No Students In System");
        return ApiResponse.success(allStudents, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{studentId}")
    public ResponseEntity<ApiResponse<String>> DeleteStudent(@PathVariable(value = "studentId") Integer studentID) {
        studentService.deleteStudent(studentID);
        return ApiResponse.success("Student deleted Successfuly", HttpStatus.OK);
    }


}
