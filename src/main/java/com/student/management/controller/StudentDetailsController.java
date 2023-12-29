package com.student.management.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.student.management.entity.StudentDetails;
import com.student.management.service.StudentDetailsService;

/**
 * REST controller for handling student details.
 */
@RestController
@RequestMapping("/api/studentDetails")
public class StudentDetailsController {

    @Autowired
    private StudentDetailsService studentDetailsService;

    /**
     * Endpoint for inserting a list of student details.
     *
     * @param studentDetails List of student details to be inserted.
     * @return ResponseEntity containing a message indicating the success or failure of the insertion.
     */
    @PostMapping("/insertData")
    public ResponseEntity<String> insertData(@RequestBody List<StudentDetails> studentDetails) {
        try {
            String message = studentDetailsService.insertData(studentDetails);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving all student details.
     *
     * @return ResponseEntity containing a list of all student details or an error message.
     */
    @GetMapping("/viewAll")
    public ResponseEntity<?> getAllData() {
        try {
            List<StudentDetails> allData = studentDetailsService.getAllData();
            return ResponseEntity.ok(allData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Endpoint for retrieving the rank of a student.
     *
     * @param name Name of the student.
     * @return ResponseEntity containing the rank of the student or an error message.
     */
    @GetMapping("/getRank")
    public ResponseEntity<?> getRank(@RequestParam String name) {
        try {
            Map<String, Object> rankResponse = studentDetailsService.getRank(name);
            return ResponseEntity.ok(rankResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    /**
     * Endpoint for updating the SAT score of a student.
     *
     * @param name     Name of the student.
     * @param newScore New SAT score for the student.
     * @return ResponseEntity containing a message indicating the success or failure of the update.
     */
    @PutMapping("/updateScore")
    public ResponseEntity<String> updateScore(@RequestParam String name, @RequestParam int newScore) {
        try {
            String message = studentDetailsService.updateScore(name, newScore);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Endpoint for deleting the record of a student.
     *
     * @param name Name of the student.
     * @return ResponseEntity containing a message indicating the success or failure of the deletion.
     */
    @DeleteMapping("/deleteRecord")
    public ResponseEntity<String> deleteRecord(@RequestParam String name) {
        try {
            String message = studentDetailsService.deleteRecord(name);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
