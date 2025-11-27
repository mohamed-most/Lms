package com.mohamedmostafa.Lms.admin;

import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import com.mohamedmostafa.Lms.mappers.StudentMapper;
import com.mohamedmostafa.Lms.services.concretes.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/admin/student")
public class AdminStudentController {
    private final StudentServiceImp studentServiceImp;
    private final StudentMapper studentMapper;


    @Autowired
    public AdminStudentController(StudentServiceImp studentServiceImp, StudentMapper studentMapper) {
        this.studentServiceImp = studentServiceImp;
        this.studentMapper = studentMapper;
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping({"", "/"})
//    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents() {
//        List<Student> allStudents = studentServiceImp.getAllStudents();
//        if (allStudents.isEmpty()) throw new ResourceNotFoundEx("No Students In System");
//        return ApiResponse.success(allStudents, HttpStatus.OK);
//    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{studentId}")
    public ResponseEntity<ApiResponse<String>> DeleteStudent(@PathVariable(value = "studentId") Integer studentID) {
        studentServiceImp.deleteStudent(studentID);
        return ApiResponse.success("Student deleted Successfuly", HttpStatus.OK);
    }


}
