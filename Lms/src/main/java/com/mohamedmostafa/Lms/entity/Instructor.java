package com.mohamedmostafa.Lms.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "instructors")
@Data
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class Instructor {


    // One instructor can teach many courses
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<Course> courses;
}
