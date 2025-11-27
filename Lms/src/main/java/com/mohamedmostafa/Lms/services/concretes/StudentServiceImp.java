package com.mohamedmostafa.Lms.services.concretes;

import com.mohamedmostafa.Lms.dtos.response.StudentResponseDto;
import com.mohamedmostafa.Lms.entity.Student;
import com.mohamedmostafa.Lms.exceptions.BadRequestException;
import com.mohamedmostafa.Lms.exceptions.ResourceNotFoundEx;
import com.mohamedmostafa.Lms.mappers.StudentMapper;
import com.mohamedmostafa.Lms.repositories.StudentRepo;
import com.mohamedmostafa.Lms.security.UserDetailsImpl;
import com.mohamedmostafa.Lms.services.abstracts.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.mohamedmostafa.Lms.mappers.StudentMapper.toResponseDto;
import static com.mohamedmostafa.Lms.messages.ControllersMessages.*;

@Service
public class StudentServiceImp implements StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentServiceImp(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    // Find a student by ID
    public StudentResponseDto findStudent(Integer studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundEx(STUDENT_NOT_FOUND_MSG));
        return toResponseDto(student);
    }


    // Get all students
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentRepo.findAll();

        return Optional.of(students).filter(list -> !list.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundEx(NO_STUDENTS_IN_SYSTEM_MSG))
                .stream().map(StudentMapper::toResponseDto).toList();
    }

    public StudentResponseDto createStudent(Student student) {

        Student createdStudent = studentRepo.findByEmail(student.getEmail()).orElseThrow(() -> {
            throw new RuntimeException(EMAIL_IN_USE_MSG);
        });

        return toResponseDto(createdStudent);

    }

    public void deleteStudent(Integer id) {
        Student existingStudent = studentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundEx(STUDENT_NOT_FOUND_MSG));
        studentRepo.delete(existingStudent);
    }


//    public StudentResponseDto findStudentByEmail(String email) {
//        Student student = studentRepo.findByEmail(email).
//                orElseThrow(() -> new ResourceNotFoundEx(EMAIL_NOT_FOUND_MSG));
//        return toResponseDto(student);
//    }

    public StudentResponseDto getStudent(Integer studentId, UserDetailsImpl loggedUser) {

        boolean isAdmin = loggedUser.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        boolean isStudent = loggedUser.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"));

        // Fetch the student once
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundEx(STUDENT_NOT_FOUND_MSG));

        if (isAdmin) {
            return toResponseDto(student);
        }

        if (isStudent) {
            if (!loggedUser.getId().equals(studentId)) {
                throw new BadRequestException("You are not allowed to access another student's information.");
            }
            return toResponseDto(student);
        }

        throw new BadRequestException("You don't have permission to perform this action.");
    }

}
