package com.example.CrudAssignment.Controller;


import com.example.CrudAssignment.Entity.Student;
import com.example.CrudAssignment.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/crud")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/getstudent")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @PostMapping("/student")
    public Student createStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @PutMapping("/student/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails){
        Optional<Student> student = studentRepository.findById(id);

        if(student.isPresent()){
            Student updateStudent = student.get();
            updateStudent.setFirstName(studentDetails.getFirstName());
            updateStudent.setLastName(studentDetails.getLastName());
            updateStudent.setEmail(studentDetails.getEmail());
            updateStudent.setAge(studentDetails.getAge());
            updateStudent.setMajor(studentDetails.getMajor());

            return studentRepository.save(updateStudent);
        }
        return null;
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentRepository.deleteById(id);
    }


}
