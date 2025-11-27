package com.mohamedmostafa.Lms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mohamedmostafa.Lms.enums.CourseStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @ManyToOne
//    @JoinColumn(name = "student_id", nullable = false)
//    private Student student;

//    @ManyToOne
//    @JoinColumn(name = "course_id", nullable = false)
//    private Course course;

//    private Double grade;

    @Enumerated(EnumType.STRING) // store enum as VARCHAR in DB
    @Column(nullable = false)
    private CourseStatus status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}

