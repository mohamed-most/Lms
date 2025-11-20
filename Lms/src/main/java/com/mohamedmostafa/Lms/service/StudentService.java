package com.mohamedmostafa.Lms.service;

import com.mohamedmostafa.Lms.entity.Student;
import com.mohamedmostafa.Lms.errors.ResourceNotFoundEx;
import com.mohamedmostafa.Lms.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    // Find a student by ID
    public Student findStudent(Integer studentId) {
        return studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundEx("Student not found with ID: " + studentId));
    }

    // Get all students
    public List<Student> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        if (students.isEmpty()) throw new ResourceNotFoundEx("there is no students in system ");
        return students;
    }

    public Student createStudent(Student student) {
        // Check if email already exists
        if (studentRepo.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Email is already in use"); // better: custom exception
        }

        // Check if username already exists
        if (studentRepo.existsByUsername(student.getUsername())) {
            throw new RuntimeException("Username is already in use");
        }

        // Save student
        return studentRepo.save(student);
    }

    // Update existing student
    public Student updateStudent(Integer id, Student updatedStudent) {
        Student existingStudent = findStudent(id); // throws exception if not found
        existingStudent.setUsername(updatedStudent.getUsername());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setPassword(updatedStudent.getPassword());
        existingStudent.setRole(updatedStudent.getRole());
        return studentRepo.save(existingStudent);
    }

    // Delete a student
    public void deleteStudent(Integer id) {
        Student existingStudent = findStudent(id); // throws exception if not found
        studentRepo.delete(existingStudent);
    }

    public Student findStudentByEamil(String email) {
        Student student = studentRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundEx("this email not in system "));

        return student;
    }
}
