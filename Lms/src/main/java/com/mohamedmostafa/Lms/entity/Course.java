package com.mohamedmostafa.Lms.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String courseCode;

    @Column(nullable = false)
    private String courseName;

    // Instructor relation
//    @ManyToOne
//    @JoinColumn(name = "instructor_id", nullable = true)
//    private Instructor instructor;

    // Students enrolled (via Enrollment entity)
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;


    @Column(name = "created_at", updatable = false)
    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

}

