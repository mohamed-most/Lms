package com.mohamedmostafa.Lms.controller;


import com.mohamedmostafa.Lms.dto.response.ApiResponse;
import com.mohamedmostafa.Lms.entity.Course;
import com.mohamedmostafa.Lms.mapper.CourseMapper;
import com.mohamedmostafa.Lms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * /api/v1/courses
 * These endpoints aiming to get all courses and update it this is mainly for the admin (later)
 */


@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseController(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses() {
        List<Course> courseList = courseService.getAllCourses();
        return ApiResponse.success(courseList, HttpStatus.OK);
    }


    @GetMapping("/{courseId}")
    public ResponseEntity<ApiResponse<Course>> getSpecificCourse(@PathVariable("courseId") Integer courseId) {
        Course course = courseService.getCourseById(courseId);
        return ApiResponse.success(course, HttpStatus.OK);
    }


}
