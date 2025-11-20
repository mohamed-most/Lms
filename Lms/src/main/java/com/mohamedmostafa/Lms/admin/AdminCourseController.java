package com.mohamedmostafa.Lms.admin;

import com.mohamedmostafa.Lms.dto.request.CourseRequestDto;
import com.mohamedmostafa.Lms.dto.response.ApiResponse;
import com.mohamedmostafa.Lms.entity.Course;
import com.mohamedmostafa.Lms.mapper.CourseMapper;
import com.mohamedmostafa.Lms.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/admin/course")
public class AdminCourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    public AdminCourseController(CourseMapper courseMapper, CourseService courseService) {
        this.courseMapper = courseMapper;
        this.courseService = courseService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping({"", "/"})
    public ResponseEntity<ApiResponse<Course>> createCourse(@RequestBody CourseRequestDto courseRequestDto) {
        Course courseMapperEntity = courseMapper.toEntity(courseRequestDto);
        Course course = courseService.createCourse(courseMapperEntity);
        return ApiResponse.success(course, HttpStatus.CREATED);
    }
}
