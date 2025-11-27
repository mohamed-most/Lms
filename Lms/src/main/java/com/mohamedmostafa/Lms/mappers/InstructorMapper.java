package com.mohamedmostafa.Lms.mappers;

import com.mohamedmostafa.Lms.dtos.response.InstructorResponseDto;
import com.mohamedmostafa.Lms.entity.Instructor;

public class InstructorMapper {

    public InstructorResponseDto toResponseDto(Instructor instructor) {
        InstructorResponseDto instructorResponseDto = InstructorResponseDto.builder().build();

        return instructorResponseDto;
    }
}
