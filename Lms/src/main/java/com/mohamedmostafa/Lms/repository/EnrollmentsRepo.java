package com.mohamedmostafa.Lms.repository;

import com.mohamedmostafa.Lms.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentsRepo extends JpaRepository<Enrollment,Integer> {
}
