package com.mohamedmostafa.Lms.repository;

import com.mohamedmostafa.Lms.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {
}
