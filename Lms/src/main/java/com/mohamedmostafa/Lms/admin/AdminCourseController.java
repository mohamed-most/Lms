package com.mohamedmostafa.Lms.admin;

import com.mohamedmostafa.Lms.dto.request.CourseRequestDto;
import com.mohamedmostafa.Lms.dto.response.ApiResponse;
import com.mohamedmostafa.Lms.dto.response.CourseResponseDto;
import com.mohamedmostafa.Lms.entity.Course;
import com.mohamedmostafa.Lms.mapper.CourseMapper;
import com.mohamedmostafa.Lms.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/admin/course")
public class AdminCourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;


    public AdminCourseController(CourseMapper courseMapper, CourseService courseService) {
        this.courseMapper = courseMapper;
        this.courseService = courseService;
    }

    /*
     * admin can add courses
     * */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping({"", "/"})
    public ResponseEntity<ApiResponse<Course>> createCourse(@Valid @RequestBody CourseRequestDto courseRequestDto) {
        Course courseMapperEntity = courseMapper.toEntity(courseRequestDto);
        Course course = courseService.createCourse(courseMapperEntity);
        return ApiResponse.success(course, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{courseId}")
    public ResponseEntity<ApiResponse<CourseResponseDto>> deleteCourse(@PathVariable int courseId) {
        Course course = courseService.deleteCourse(courseId);
        CourseResponseDto responseDto = CourseResponseDto.builder()
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .build();
        return ApiResponse.success(responseDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{courseId}")
    public ResponseEntity<ApiResponse<CourseResponseDto>> updateCourse(@PathVariable Integer courseId,
                                                                       @Valid @RequestBody CourseRequestDto courseRequestDto
    ) {
        Course course = courseService.updateCourseById(courseId, courseRequestDto);
        CourseResponseDto courseResponseDto = CourseResponseDto.builder()
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .build();
        return ApiResponse.success(courseResponseDto, HttpStatus.OK);
    }
}
