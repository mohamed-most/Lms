package com.mohamedmostafa.Lms.controllers;


import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import com.mohamedmostafa.Lms.entity.Course;
import com.mohamedmostafa.Lms.mappers.CourseMapper;
import com.mohamedmostafa.Lms.services.concretes.CourseServiceImp;
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

    private final CourseServiceImp courseServiceImp;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseController(CourseServiceImp courseServiceImp, CourseMapper courseMapper) {
        this.courseServiceImp = courseServiceImp;
        this.courseMapper = courseMapper;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses() {
        List<Course> courseList = courseServiceImp.getAllCourses();
        return ApiResponse.success(courseList, HttpStatus.OK);
    }


    @GetMapping("/{courseId}")
    public ResponseEntity<ApiResponse<Course>> getSpecificCourse(@PathVariable("courseId") Integer courseId) {
        Course course = courseServiceImp.getCourseById(courseId);
        return ApiResponse.success(course, HttpStatus.OK);
    }


}
