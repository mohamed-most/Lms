package com.mohamedmostafa.Lms.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Data
@DiscriminatorValue("INSTRUCTOR")
public class Instructor extends User {


    // One instructor can teach many courses
//    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
//    private List<Course> courses;


    private String specialization;
    private String bio;
    private Integer yearsOfExperience;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
