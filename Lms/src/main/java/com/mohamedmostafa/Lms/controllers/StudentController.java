package com.mohamedmostafa.Lms.controllers;


import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import com.mohamedmostafa.Lms.dtos.response.StudentResponseDto;
import com.mohamedmostafa.Lms.security.UserDetailsImpl;
import com.mohamedmostafa.Lms.services.abstracts.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;

    }


    //TODO:add functionality to check if the user that see student is are equals
    @GetMapping("/{studentId}")
    public ResponseEntity<ApiResponse<StudentResponseDto>> getStudent(
            @PathVariable Integer studentId,
            @AuthenticationPrincipal UserDetailsImpl loggedUser) {

    }


//    @PostMapping({"/", ""})
//    public ResponseEntity<ApiResponse<StudentResponseDto>> createStudent(@RequestBody StudentSignUpRequestDto signUpDto) {
//        studentService.createStudent(signUpDto);
//    }
//

}
