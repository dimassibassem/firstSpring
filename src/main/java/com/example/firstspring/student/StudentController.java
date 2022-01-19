package com.example.firstspring.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {
    private final StudentService StudentService;
@Autowired
    public StudentController(StudentService StudentService) {
        this.StudentService = StudentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return StudentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        StudentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long StudentId) {
    StudentService.deleteStudent(StudentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId ,
            @RequestParam (required = false) String name ,
            @RequestParam (required = false) String email){
    StudentService.updateStudent(studentId, name, email);
    }

}


