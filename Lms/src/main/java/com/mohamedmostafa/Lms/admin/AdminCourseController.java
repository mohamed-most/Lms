package com.mohamedmostafa.Lms.admin;

import com.mohamedmostafa.Lms.dtos.request.CourseRequestDto;
import com.mohamedmostafa.Lms.dtos.response.ApiResponse;
import com.mohamedmostafa.Lms.dtos.response.CourseResponseDto;
import com.mohamedmostafa.Lms.services.abstracts.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/admin/course")
public class AdminCourseController {

    private final CourseService courseService;


    public AdminCourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /*
     * admin can add courses
     * */
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping({"", "/"})
    public ResponseEntity<ApiResponse<CourseResponseDto>> createCourse(@Valid @RequestBody CourseRequestDto courseRequestDto) {
        return ApiResponse.success(courseService.createCourse(courseRequestDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{courseId}")
    public ResponseEntity<ApiResponse<CourseResponseDto>> deleteCourse(@PathVariable int courseId) {
        return ApiResponse.success(courseService.deleteCourse(courseId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{courseId}")
    public ResponseEntity<ApiResponse<CourseResponseDto>> updateCourse(@PathVariable Integer courseId,
                                                                       @Valid @RequestBody CourseRequestDto courseRequestDto
    ) {

        return ApiResponse.success(courseService.updateCourseById(courseId, courseRequestDto), HttpStatus.OK);
    }
}
