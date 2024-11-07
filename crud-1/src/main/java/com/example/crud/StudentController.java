package com.example.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController  // ใช้ @RestController เพื่อให้ Spring Boot รู้ว่าเป็น Controller สำหรับ REST API
@RequestMapping("/api/students")  // ระบุ path หลักของ API สำหรับ students
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // POST /api/students/add: เพิ่ม Student ใหม่
    @PostMapping("/add")  // ใช้ @PostMapping สำหรับการเพิ่มข้อมูล
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);  // ส่งคืนข้อมูลพร้อมสถานะ CREATED
    }

    // GET /api/students/email/{email}: ดึงข้อมูล Student ตาม email
    @GetMapping("/email/{email}")  // ใช้ @GetMapping สำหรับการดึงข้อมูล
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
        Student student = studentService.getStudentByEmail(email);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);  // ส่งคืนข้อมูลสถานะ OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // ถ้าไม่พบข้อมูล ส่งคืนสถานะ 404
        }
    }
}
