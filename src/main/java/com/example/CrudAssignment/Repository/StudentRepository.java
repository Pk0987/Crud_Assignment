package com.example.CrudAssignment.Repository;

import com.example.CrudAssignment.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
