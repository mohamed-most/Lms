package com.mohamedmostafa.Lms.admin.courses;

import com.mohamedmostafa.Lms.dtos.request.CourseRequestDto;
import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import com.mohamedmostafa.Lms.dtos.response.CourseResponseDto;
import com.mohamedmostafa.Lms.entity.Course;
import com.mohamedmostafa.Lms.mappers.CourseMapper;
import com.mohamedmostafa.Lms.services.concretes.CourseServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/admin/course")
public class AdminCourseController {

    private final CourseServiceImp courseServiceImp;
    private final CourseMapper courseMapper;


    public AdminCourseController(CourseMapper courseMapper, CourseServiceImp courseServiceImp) {
        this.courseMapper = courseMapper;
        this.courseServiceImp = courseServiceImp;
    }

    /*
     * admin can add courses
     * */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping({"", "/"})
    public ResponseEntity<ApiResponse<Course>> createCourse(@Valid @RequestBody CourseRequestDto courseRequestDto) {
        Course courseMapperEntity = courseMapper.toEntity(courseRequestDto);
        Course course = courseServiceImp.createCourse(courseMapperEntity);
        return ApiResponse.success(course, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{courseId}")
    public ResponseEntity<ApiResponse<CourseResponseDto>> deleteCourse(@PathVariable int courseId) {
        Course course = courseServiceImp.deleteCourse(courseId);
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
        Course course = courseServiceImp.updateCourseById(courseId, courseRequestDto);
        CourseResponseDto courseResponseDto = CourseResponseDto.builder()
                .courseCode(course.getCourseCode())
                .courseName(course.getCourseName())
                .build();
        return ApiResponse.success(courseResponseDto, HttpStatus.OK);
    }
}
