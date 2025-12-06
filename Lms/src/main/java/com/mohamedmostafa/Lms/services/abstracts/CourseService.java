package com.mohamedmostafa.Lms.services.abstracts;

import com.mohamedmostafa.Lms.dtos.request.CourseRequestDto;
import com.mohamedmostafa.Lms.dtos.response.CourseResponseDto;
import com.mohamedmostafa.Lms.entity.Course;

import java.util.List;

public interface CourseService {
    public List<Course> getAllCourses();

    public Course getCourseById(Integer courseId);


    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto);

    public CourseResponseDto deleteCourse(Integer courseId);


    public CourseResponseDto updateCourseById(Integer courseId, CourseRequestDto courseRequestDto);
}
