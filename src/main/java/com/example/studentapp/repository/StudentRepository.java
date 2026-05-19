package com.example.studentapp.repository;

import com.example.studentapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Returns 10 random student names from the database.
     * Uses PostgreSQL's random() function for true randomness.
     */
    @Query(value = "SELECT * FROM students ORDER BY RANDOM() LIMIT 10", nativeQuery = true)
    List<Student> findRandomStudents();
}
