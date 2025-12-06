package com.mohamedmostafa.Lms.dtos.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentResponseDto {
    //    private Integer id;
    private String departmentName;
    private String departmentCode;

    // Optional: list of instructors in this department
//    private List<InstructorResponseDto> instructors;
}
