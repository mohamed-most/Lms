package com.mohamedmostafa.Lms.repository;

import com.mohamedmostafa.Lms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

    // Explicitly declare findById (optional, already exists in JpaRepository)
    Optional<Student> findById(Integer id);

    // Find student by email (used for login or uniqueness check)
    Optional<Student> findByEmail(String email);

    // Check if student exists by email
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
    // You can add other custom queries if needed
    // e.g., find students by role
    // List<Student> findByRole(Role role);
}
