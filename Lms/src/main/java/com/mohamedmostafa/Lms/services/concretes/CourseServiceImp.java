package com.mohamedmostafa.Lms.services.concretes;


import com.mohamedmostafa.Lms.dtos.request.CourseRequestDto;
import com.mohamedmostafa.Lms.dtos.response.CourseResponseDto;
import com.mohamedmostafa.Lms.entity.Course;
import com.mohamedmostafa.Lms.exceptions.ResourceNotFoundEx;
import com.mohamedmostafa.Lms.mappers.CourseMapper;
import com.mohamedmostafa.Lms.repositories.CourseRepo;
import com.mohamedmostafa.Lms.services.abstracts.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp implements CourseService {

    private final CourseRepo courseRepo;

    @Autowired
    public CourseServiceImp(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll(); // return empty list, not exception
    }

    public Course getCourseById(Integer courseId) {
        return courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundEx(
                        "Course not found with id: " + courseId
                ));
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

