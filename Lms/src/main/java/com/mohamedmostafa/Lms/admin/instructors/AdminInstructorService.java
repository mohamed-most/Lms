package com.mohamedmostafa.Lms.admin.instructors;


import com.mohamedmostafa.Lms.dtos.request.InstructorRequestDto;
import com.mohamedmostafa.Lms.dtos.response.InstructorResponseDto;
import com.mohamedmostafa.Lms.entity.Department;
import com.mohamedmostafa.Lms.entity.Instructor;
import com.mohamedmostafa.Lms.exceptions.ResourceNotFoundEx;
import com.mohamedmostafa.Lms.mappers.InstructorMapper;
import com.mohamedmostafa.Lms.repositories.DepartmentRepo;
import com.mohamedmostafa.Lms.repositories.InstructorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mohamedmostafa.Lms.mappers.InstructorMapper.toInstructor;
import static com.mohamedmostafa.Lms.mappers.InstructorMapper.toResponseDto;

@Service
public class AdminInstructorService {

    private final PasswordEncoder passwordEncoder;
    private final InstructorRepo instructorRepo;
    private final DepartmentRepo departmentRepo;

    @Autowired
    AdminInstructorService(PasswordEncoder passwordEncoder, InstructorRepo instructorRepo, DepartmentRepo departmentRepo) {
        this.passwordEncoder = passwordEncoder;
        this.instructorRepo = instructorRepo;
        this.departmentRepo = departmentRepo;
    }

    public InstructorResponseDto createInstructor(InstructorRequestDto instructorRequestDto) {
        Department department = departmentRepo.findById(instructorRequestDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundEx("this department not in sys "));
        Instructor instructor = toInstructor(instructorRequestDto, departmentRepo);
        instructor.setPassword(passwordEncoder.encode(instructor.getPassword()));
        instructorRepo.save(instructor);
        return toResponseDto(instructor);
    }

    public List<InstructorResponseDto> getAllInstructors() {
        List<Instructor> instructors = instructorRepo.findAll();

        if (instructors.isEmpty()) {
            throw new RuntimeException("No instructors found"); // or a custom exception
        }

        return instructors.stream()
                .map(InstructorMapper::toResponseDto)
                .toList();
    }

}
