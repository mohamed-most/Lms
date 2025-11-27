package com.mohamedmostafa.Lms.services.concretes;


import com.mohamedmostafa.Lms.dtos.request.CourseRequestDto;
import com.mohamedmostafa.Lms.entity.Course;
import com.mohamedmostafa.Lms.exceptions.ResourceNotFoundEx;
import com.mohamedmostafa.Lms.repositories.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImp {

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

    public Course createCourse(Course course) {
        return courseRepo.save(course);
    }

    public Course deleteCourse(Integer courseId) {

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundEx(
                        "Course not found with id: " + courseId
                ));

        courseRepo.delete(course);
        return course;
    }

    public Course updateCourseById(Integer courseId, CourseRequestDto courseRequestDto) {

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundEx(
                        "Course not found with id: " + courseId
                ));

        course.setCourseCode(courseRequestDto.getCourseCode());
        course.setCourseName(courseRequestDto.getCourseName());

        return courseRepo.save(course);
    }

}

