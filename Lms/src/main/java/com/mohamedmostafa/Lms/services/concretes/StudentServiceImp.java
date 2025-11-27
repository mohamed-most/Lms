package com.mohamedmostafa.Lms.services.concretes;

import com.mohamedmostafa.Lms.dtos.response.StudentResponseDto;
import com.mohamedmostafa.Lms.entity.Student;
import com.mohamedmostafa.Lms.exceptions.BadRequestException;
import com.mohamedmostafa.Lms.exceptions.ResourceNotFoundEx;
import com.mohamedmostafa.Lms.repositories.StudentRepo;
import com.mohamedmostafa.Lms.security.UserDetailsImpl;
import com.mohamedmostafa.Lms.services.abstracts.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static com.mohamedmostafa.Lms.mappers.StudentMapper.toResponseDto;
import static com.mohamedmostafa.Lms.messages.ControllersMessages.STUDENT_NOT_FOUND_MSG;

@Service
public class StudentServiceImp implements StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentServiceImp(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    public StudentResponseDto getStudent(Integer studentId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loggedUser = (UserDetailsImpl) auth.getPrincipal();

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
