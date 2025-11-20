package com.mohamedmostafa.Lms.repository;

import com.mohamedmostafa.Lms.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InstructorRepo extends JpaRepository<Instructor,Integer> {

}
