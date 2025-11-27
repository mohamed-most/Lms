package com.mohamedmostafa.Lms.services.concretes;

import org.springframework.stereotype.Service;

@Service
public class EnrollmentServiceImp {
//
//    private final CourseRepo courseRepo;
//    private final StudentRepo studentRepo;
//    private final EnrollmentsRepo enrollmentsRepo;
//
//
//    @Autowired
//    public EnrollmentServiceImp(CourseRepo courseRepo, StudentRepo studentRepo, EnrollmentsRepo enrollmentsRepo) {
//        this.enrollmentsRepo = enrollmentsRepo;
//        this.studentRepo = studentRepo;
//        this.courseRepo = courseRepo;
//    }
//
//    public Enrollment enrollStudent(Integer studentId, Integer courseId) {
//        Student student = studentRepo.findById(studentId)
//                .orElseThrow(() -> new RuntimeException("Student not found"));
//        Course course = courseRepo.findById(courseId)
//                .orElseThrow(() -> new RuntimeException("Course not found"));
//        if (enrollmentsRepo.existsByStudentAndCourse(student, course)) {
//            throw new RuntimeException("Student already enrolled in this course");
//        }
//        Enrollment enrollment = Enrollment.builder()
//                .student(student)
//                .course(course)
//                .status(CourseStatus.ACTIVE) // default one
//                .build();
//
//        return enrollmentsRepo.save(enrollment);
//    }
//
//    public Enrollment withdrawStudent(Integer studentId, Integer courseId) {
//        // Find enrollment
//        Enrollment enrollment = enrollmentsRepo.findByStudentIdAndCourseId(studentId, courseId)
//                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
//
//        // Update status to DROPPED
//        enrollment.setStatus(CourseStatus.WITHDRAW);
//        return enrollmentsRepo.save(enrollment);
//    }


}
