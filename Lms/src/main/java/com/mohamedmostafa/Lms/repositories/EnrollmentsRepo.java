package com.mohamedmostafa.Lms.repositories;

import com.mohamedmostafa.Lms.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentsRepo extends JpaRepository<Enrollment, Integer> {
//    boolean existsByStudentAndCourse(Student student, Course course);

//    Optional<Enrollment> findByStudentIdAndCourseId(Integer studentId, Integer courseId);


}
