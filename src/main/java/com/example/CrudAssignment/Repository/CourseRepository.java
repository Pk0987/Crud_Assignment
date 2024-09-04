package com.example.CrudAssignment.Repository;

import com.example.CrudAssignment.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
