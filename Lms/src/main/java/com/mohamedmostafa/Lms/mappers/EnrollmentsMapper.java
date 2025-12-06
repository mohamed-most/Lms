package com.mohamedmostafa.Lms.mappers;

import com.mohamedmostafa.Lms.dtos.request.EnrollmentRequestDto;
import com.mohamedmostafa.Lms.dtos.response.EnrollmentResponseDto;
import com.mohamedmostafa.Lms.entity.Course;
import com.mohamedmostafa.Lms.entity.Enrollment;
import com.mohamedmostafa.Lms.entity.Student;
import com.mohamedmostafa.Lms.enums.CourseStatus;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EnrollmentsMapper {

    public static EnrollmentResponseDto toResponseDto(Enrollment enrollment) {

        return EnrollmentResponseDto.builder()
                .course(CourseMapper.toResponseDto(enrollment.getCourse()))
                .status(enrollment.getStatus())
                .student(StudentMapper.toResponseDto(enrollment.getStudent()))
                .createdAt(enrollment.getCreatedAt())
                .build();
    }

    public static Enrollment toEntity(EnrollmentRequestDto enrollmentRequestDto, Student student, Course course) {
        return Enrollment.builder()
                .student(student)           // student fetched from JWT
                .course(course)             // course fetched using courseId
                .status(CourseStatus.ACTIVE) // default status
                .build();
    }


}
