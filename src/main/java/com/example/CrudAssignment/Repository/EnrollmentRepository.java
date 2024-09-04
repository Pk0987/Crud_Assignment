package com.example.CrudAssignment.Repository;

import com.example.CrudAssignment.Entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
}
