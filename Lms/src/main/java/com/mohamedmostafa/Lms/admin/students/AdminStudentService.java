package com.mohamedmostafa.Lms.admin.students;

import com.mohamedmostafa.Lms.dtos.request.StudentSignUpRequestDto;
import com.mohamedmostafa.Lms.dtos.response.StudentResponseDto;
import com.mohamedmostafa.Lms.entity.Student;
import com.mohamedmostafa.Lms.exceptions.ResourceNotFoundEx;
import com.mohamedmostafa.Lms.mappers.StudentMapper;
import com.mohamedmostafa.Lms.repositories.StudentRepo;
import com.mohamedmostafa.Lms.services.abstracts.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.mohamedmostafa.Lms.mappers.StudentMapper.toResponseDto;
import static com.mohamedmostafa.Lms.messages.ControllersMessages.*;

@Service
public class AdminStudentService {

    private final StudentService studentService;
    private final StudentRepo studentRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminStudentService(StudentService studentService, StudentRepo studentRepo, PasswordEncoder passwordEncoder) {
        this.studentService = studentService;
        this.studentRepo = studentRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // Find a student by ID
    public StudentResponseDto getStudent(Integer studentId) {
        return studentService.getStudent(studentId);
    }


    // Get all students
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentRepo.findAll();

        return Optional.of(students).filter(list -> !list.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundEx(NO_STUDENTS_IN_SYSTEM_MSG))
                .stream().map(StudentMapper::toResponseDto).toList();
    }


    public StudentResponseDto createStudent(StudentSignUpRequestDto studentSignUpRequestDto) {
        // Check if email already exists
        if (studentRepo.findByEmail(studentSignUpRequestDto.getEmail()).isPresent()) {
            throw new RuntimeException(EMAIL_IN_USE_MSG);
        }
        System.out.println(studentSignUpRequestDto.getEmail());
        System.out.println(studentSignUpRequestDto.getPassword());
        System.out.println(studentSignUpRequestDto.getUsername());
        // Create new student
        Student newStudent = Student.builder()
                .email(studentSignUpRequestDto.getEmail())
                .username(studentSignUpRequestDto.getUsername())
                .password(passwordEncoder.encode(studentSignUpRequestDto.getPassword()))
                .build();
        System.out.println(newStudent);
        studentRepo.save(newStudent);
        return toResponseDto(newStudent);
    }

    @Transactional
    public void deleteStudent(Integer id) {
        Student existingStudent = studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundEx(STUDENT_NOT_FOUND_MSG));
        studentRepo.delete(existingStudent);
    }


}
