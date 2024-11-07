package com.example.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentDTO studentDTO) {
        Student student = new Student();
        student.setUserName(studentDTO.getUserName());
        student.setType(studentDTO.getType());
        student.setEngName(studentDTO.getEngName());
        student.setEmail(studentDTO.getEmail());
        student.setFaculty(studentDTO.getFaculty());

        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.ok(savedStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudent(id);
        return student.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}