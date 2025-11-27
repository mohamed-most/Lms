package com.mohamedmostafa.Lms.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@Entity
@SuperBuilder
@DiscriminatorValue("INSTRUCTOR")
public class Instructor extends User {


    // One instructor can teach many courses
//    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
//    private List<Course> courses;


    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
