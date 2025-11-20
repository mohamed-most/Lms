package com.mohamedmostafa.Lms.service;

import com.mohamedmostafa.Lms.entity.Student;
import com.mohamedmostafa.Lms.errors.ResourceNotFoundEx;
import com.mohamedmostafa.Lms.repository.StudentRepo;
import com.mohamedmostafa.Lms.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepo studentRepo;

    @Autowired
    public CustomUserDetailsService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws ResourceNotFoundEx {
        Student student = studentRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundEx("No student with email: " + email));

        return new UserDetailsImpl(student.getEmail(), student.getPassword(), student.getRole());
    }
}
