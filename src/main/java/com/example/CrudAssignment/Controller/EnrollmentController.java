package com.example.CrudAssignment.Controller;


import com.example.CrudAssignment.Entity.Course;
import com.example.CrudAssignment.Entity.Enrollment;
import com.example.CrudAssignment.Entity.Student;
import com.example.CrudAssignment.Repository.CourseRepository;
import com.example.CrudAssignment.Repository.EnrollmentRepository;
import com.example.CrudAssignment.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Enrollment> enrollStudent(@RequestParam Long studentId, @RequestParam Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = new Enrollment(student, course, LocalDate.now());
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        return ResponseEntity.ok(savedEnrollment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
        return enrollmentRepository.findById(id)
                .map(enrollment -> ResponseEntity.ok().body(enrollment))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable Long id) {
        return enrollmentRepository.findById(id)
                .map(enrollment -> {
                    enrollmentRepository.delete(enrollment);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
