package com.mohamedmostafa.Lms.services.concretes;

import com.mohamedmostafa.Lms.dtos.request.EnrollmentRequestDto;
import com.mohamedmostafa.Lms.dtos.response.EnrollmentResponseDto;
import com.mohamedmostafa.Lms.entity.Course;
import com.mohamedmostafa.Lms.entity.Enrollment;
import com.mohamedmostafa.Lms.entity.Student;
import com.mohamedmostafa.Lms.entity.UserDetailsCustomized;
import com.mohamedmostafa.Lms.enums.CourseStatus;
import com.mohamedmostafa.Lms.exceptions.ResourceNotFoundEx;
import com.mohamedmostafa.Lms.mappers.EnrollmentsMapper;
import com.mohamedmostafa.Lms.repositories.CourseRepo;
import com.mohamedmostafa.Lms.repositories.EnrollmentsRepo;
import com.mohamedmostafa.Lms.repositories.StudentRepo;
import com.mohamedmostafa.Lms.services.abstracts.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnrollmentServiceImp implements EnrollmentService {

    private final EnrollmentsRepo enrollmentsRepo;
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    @Autowired
    public EnrollmentServiceImp(EnrollmentsRepo enrollmentsRepo, StudentRepo studentRepo
            , CourseRepo courseRepo
    ) {
        this.enrollmentsRepo = enrollmentsRepo;
        this.courseRepo = courseRepo;
        this.studentRepo = studentRepo;
    }


    @Override
    public EnrollmentResponseDto enrolStudentInCourse(final EnrollmentRequestDto enrollmentRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsCustomized userDetails = (UserDetailsCustomized) authentication.getPrincipal();

        Integer studentId = userDetails.getUserId();
        Integer courseId = enrollmentRequest.getCourseId();

        Student student = studentRepo.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundEx("This student is not found")
        );

        Course course = courseRepo.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundEx("This course is not found")
        );


        Optional<Enrollment> existing = enrollmentsRepo
                .findByStudentIdAndCourseId(studentId, courseId);

        if (existing.isPresent()) {
            throw new IllegalStateException("Student already enrolled in this course");
        }


        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .status(CourseStatus.ACTIVE)
                .build();

        Enrollment saved = enrollmentsRepo.save(enrollment);

        return EnrollmentsMapper.toResponseDto(saved);
    }


    @Override
    public EnrollmentResponseDto changeCourseStatus(EnrollmentRequestDto enrollmentRequest) {

        // 1. Get the current authenticated student
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsCustomized userDetails = (UserDetailsCustomized) authentication.getPrincipal();

        Integer studentId = userDetails.getUserId();
        Integer courseId = enrollmentRequest.getCourseId();
        CourseStatus newStatus = enrollmentRequest.getStatus();

        // 2. Validate student exists (optional if you're sure JWT is valid)
        Student student = studentRepo.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundEx("Student not found")
        );

        // 3. Validate course exists
        Course course = courseRepo.findById(courseId).orElseThrow(
                () -> new ResourceNotFoundEx("Course not found")
        );

        // 4. Check that enrollment exists
        Enrollment enrollment = enrollmentsRepo
                .findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(
                        () -> new ResourceNotFoundEx("You are not enrolled in this course")
                );

        // 5. Update status
        enrollment.setStatus(newStatus);

        // 6. Save updated enrollment
        Enrollment updatedEnrollment = enrollmentsRepo.save(enrollment);

        // 7. Map to response DTO
        return EnrollmentsMapper.toResponseDto(updatedEnrollment);

    }

}
