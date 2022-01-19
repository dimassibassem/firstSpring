package com.example.firstspring.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class StudentService {
    private final StudentRepository StudentRepository;

    @Autowired
    public StudentService(com.example.firstspring.student.StudentRepository studentRepository) {
        StudentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return StudentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> StudentOptional = StudentRepository.findStudentByEmail(student.getEmail());
        if (StudentOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        StudentRepository.save(student);
    }

    public void deleteStudent(Long StudentId) {
        boolean exists = StudentRepository.existsById(StudentId);
        if (!exists) {
            throw new IllegalStateException("Student with id: " + StudentId + " not found");
        }
        StudentRepository.deleteById(StudentId);
    }

    @Transactional
    public void updateStudent(Long StudentId, String name, String email) {
        Student student = StudentRepository.findById(StudentId).orElseThrow(() -> new IllegalStateException("Student with id: " + StudentId + " not found"));
        if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = StudentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
