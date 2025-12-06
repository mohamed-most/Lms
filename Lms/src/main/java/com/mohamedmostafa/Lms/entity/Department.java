package com.mohamedmostafa.Lms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Department {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    @NotBlank
    @NotNull
    @Column(unique = true)
    private String department_name;


    @NotNull
    @NotBlank
    @Column(unique = true)
    private String department_code;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Instructor> instructors;
}
