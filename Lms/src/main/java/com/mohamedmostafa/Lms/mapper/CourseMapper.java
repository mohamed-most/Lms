package com.mohamedmostafa.Lms.mapper;

import com.mohamedmostafa.Lms.dto.request.CourseRequestDto;
import com.mohamedmostafa.Lms.dto.response.CourseResponseDto;
import com.mohamedmostafa.Lms.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    // Map request DTO to entity
    public Course toEntity(CourseRequestDto courseRequestDto) {
        return Course.builder()
                .courseName(courseRequestDto.getCourseName())
                .courseCode(courseRequestDto.getCourseCode())
                .build();
    }

    // Map entity to response DTO
    public CourseResponseDto toDto(Course course) {
        return CourseResponseDto.builder()
                .courseName(course.getCourseName())
                .courseCode(course.getCourseCode())
                .build();
    }
}
