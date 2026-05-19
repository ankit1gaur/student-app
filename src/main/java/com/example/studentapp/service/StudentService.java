package com.example.studentapp.service;

import com.example.studentapp.model.Student;
import com.example.studentapp.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private static final Logger log = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // ── Seed the DB on startup if it is empty ─────────────────
    @PostConstruct
    public void seedDatabaseIfEmpty() {
        if (studentRepository.count() == 0) {
            log.info("No students found — seeding database with sample data...");

            List<Student> students = List.of(
                new Student("Aarav Sharma",      "aarav.sharma@college.in"),
                new Student("Priya Patel",       "priya.patel@college.in"),
                new Student("Rohan Mehta",       "rohan.mehta@college.in"),
                new Student("Ananya Iyer",       "ananya.iyer@college.in"),
                new Student("Vikram Singh",      "vikram.singh@college.in"),
                new Student("Sneha Gupta",       "sneha.gupta@college.in"),
                new Student("Arjun Nair",        "arjun.nair@college.in"),
                new Student("Kavya Reddy",       "kavya.reddy@college.in"),
                new Student("Karan Joshi",       "karan.joshi@college.in"),
                new Student("Divya Kulkarni",    "divya.kulkarni@college.in"),
                new Student("Ravi Verma",        "ravi.verma@college.in"),
                new Student("Pooja Bhatia",      "pooja.bhatia@college.in"),
                new Student("Akash Yadav",       "akash.yadav@college.in"),
                new Student("Meera Pillai",      "meera.pillai@college.in"),
                new Student("Siddharth Mishra",  "siddharth.mishra@college.in"),
                new Student("Nisha Agarwal",     "nisha.agarwal@college.in"),
                new Student("Raj Kapoor",        "raj.kapoor@college.in"),
                new Student("Tanvi Desai",       "tanvi.desai@college.in"),
                new Student("Aditya Kumar",      "aditya.kumar@college.in"),
                new Student("Swati Chatterjee",  "swati.chatterjee@college.in")
            );

            studentRepository.saveAll(students);
            log.info("Seeded {} students into the database.", students.size());
        } else {
            log.info("Database already has {} student(s). Skipping seed.", studentRepository.count());
        }
    }

    // ── Business Methods ──────────────────────────────────────

    /**
     * Returns a random list of students from the Render PostgreSQL DB.
     */
    public List<Student> getRandomStudents() {
        return studentRepository.findRandomStudents();
    }

    /**
     * Returns ALL students (useful for admin/debug).
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
