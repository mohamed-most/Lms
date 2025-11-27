package com.mohamedmostafa.Lms.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@Entity
@Getter
@Setter
@SuperBuilder
@DiscriminatorValue("STUDENT")
public class Student extends User {

//
//    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
//    private List<Enrollment> enrollments;


}
