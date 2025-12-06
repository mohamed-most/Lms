package com.mohamedmostafa.Lms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
public class Department {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    @NotBlank
    @NotNull
    private String department_name;

    @NotNull
    @NotBlank
    private String department_code;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Instructor> instructors;
}
