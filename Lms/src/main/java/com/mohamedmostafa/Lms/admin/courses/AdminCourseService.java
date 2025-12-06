package com.mohamedmostafa.Lms.admin.courses;

import com.mohamedmostafa.Lms.dtos.request.CourseRequestDto;
import com.mohamedmostafa.Lms.dtos.response.CourseResponseDto;
import com.mohamedmostafa.Lms.entity.Course;
import com.mohamedmostafa.Lms.exceptions.ResourceNotFoundEx;
import com.mohamedmostafa.Lms.mappers.CourseMapper;
import com.mohamedmostafa.Lms.repositories.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminCourseService {

    private final CourseRepo courseRepo;

    @Autowired
    public AdminCourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }


    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {
        return CourseMapper.toResponseDto(courseRepo.save(CourseMapper.toEntity(courseRequestDto)));
    }

    public CourseResponseDto deleteCourse(Integer courseId) {

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundEx(
                        "Course not found with id: " + courseId
                ));

        courseRepo.delete(course);
        return CourseMapper.toResponseDto(course);
    }

    public CourseResponseDto updateCourseById(Integer courseId, CourseRequestDto courseRequestDto) {

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundEx(
                        "Course not found with id: " + courseId
                ));

        course.setCourseCode(courseRequestDto.getCourseCode());
        course.setCourseName(courseRequestDto.getCourseName());

        return CourseResponseDto.builder()
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .build();

    }
}
