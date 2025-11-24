package com.mohamedmostafa.Lms.mapper;

import com.mohamedmostafa.Lms.dto.response.CourseResponseDto;
import com.mohamedmostafa.Lms.dto.response.EnrollmentResponse;
import com.mohamedmostafa.Lms.dto.response.StudentResponseDto;
import com.mohamedmostafa.Lms.entity.Course;
import com.mohamedmostafa.Lms.entity.Enrollment;
import com.mohamedmostafa.Lms.entity.Student;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EnrollmentsMapper {

    public EnrollmentResponse toEnrollmentsResponse(Enrollment enrollment) {
        Student student = enrollment.getStudent();
        Course course = enrollment.getCourse();

        // Build student DTO
        StudentResponseDto studentDTO = StudentResponseDto.builder()
                .username(student.getUsername())
                .email(student.getEmail())
                .build();

        // Build course DTO
        CourseResponseDto courseDTO = CourseResponseDto.builder()
                .courseName(course.getCourseName())
                .courseCode(course.getCourseCode())
                .build();

        // Build enrollment response
        EnrollmentResponse enrollmentResponse = EnrollmentResponse.builder()
                .enrollmentId(enrollment.getId())
                .student(studentDTO)
                .course(courseDTO)
                .createdAt(enrollment.getCreatedAt())
                .status(enrollment.getStatus())
                .build();

        return enrollmentResponse;
    }

}
