package com.example.CrudAssignment.Controller;

import com.example.CrudAssignment.Entity.Course;
import com.example.CrudAssignment.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/crud")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        Optional<Course> course = courseRepository.findById(id);

        if (course.isPresent()) {
            Course updatedCourse = course.get();
            updatedCourse.setCourseName(courseDetails.getCourseName());
            updatedCourse.setCourseCode(courseDetails.getCourseCode());
            updatedCourse.setDescription(courseDetails.getDescription());
            updatedCourse.setCredits(courseDetails.getCredits());

            return courseRepository.save(updatedCourse);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseRepository.deleteById(id);
    }
}
