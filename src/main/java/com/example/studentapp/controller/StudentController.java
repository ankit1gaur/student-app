package com.example.studentapp.controller;

import com.example.studentapp.model.Student;
import com.example.studentapp.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")   // Allow requests from any origin (frontend)
public class StudentController {

    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * GET /api/students/random
     * Returns a list of random student names from the Render PostgreSQL database.
     */
    @GetMapping("/students/random")
    public ResponseEntity<List<Student>> getRandomStudents() {
        log.info("GET /api/students/random — fetching random students from DB");
        List<Student> students = studentService.getRandomStudents();
        log.info("Returning {} random students", students.size());
        return ResponseEntity.ok(students);
    }

    /**
     * GET /api/students
     * Returns ALL students (handy for debugging).
     */
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        log.info("GET /api/students — fetching all students from DB");
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    /**
     * GET /api/health
     * Simple health-check endpoint.
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Student App is running ✅");
    }
}
