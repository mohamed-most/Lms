package com.mohamedmostafa.Lms.service;


import com.mohamedmostafa.Lms.entity.Course;
import com.mohamedmostafa.Lms.errors.ResourceNotFoundEx;
import com.mohamedmostafa.Lms.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepo courseRepo;


    @Autowired
    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }


    public List<Course> getAllCourses() {
        List<Course> courseList = courseRepo.findAll();
        if (courseList.isEmpty()) {
            throw new ResourceNotFoundEx("there are no courses in the system yet ");
        }
        return courseList;
    }

    public Course getSpecificCourse(Integer courseId) {
        return courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundEx("There is no course with id: " + courseId));
    }

    public Course createCourse(Course course) {
        if (course == null) throw new RuntimeException("the course body is empty ");
        return courseRepo.save(course);
    }

}
