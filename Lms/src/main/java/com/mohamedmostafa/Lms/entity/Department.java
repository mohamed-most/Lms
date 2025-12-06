package com.mohamedmostafa.Lms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private Integer id;

    @NotBlank
    @NotNull
    private String department_name;

    @NotNull
    @NotBlank
    private String department_code;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Instructor> instructors;
}
