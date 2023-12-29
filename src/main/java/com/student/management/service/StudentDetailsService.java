package com.student.management.service;

import java.util.List;
import java.util.Map;

import com.student.management.entity.StudentDetails;

/**
 * Service interface for managing student details.
 */
public interface StudentDetailsService {

    /**
     * Inserts a list of student details into the system.
     *
     * @param studentDetailsList List of student details to be inserted.
     * @return A message indicating the success or failure of the insertion.
     */
    String insertData(List<StudentDetails> studentDetailsList);

    /**
     * Retrieves all student details from the system.
     *
     * @return List of all student details.
     */
    List<StudentDetails> getAllData();

    /**
     * Gets the rank of a student based on their SAT score.
     *
     * @param name Name of the student.
     * @return The rank of the student or -1 if not found.
     */
    Map<String, Object> getRank(String name);

    /**
     * Updates the SAT score and result for a student.
     *
     * @param name     Name of the student.
     * @param newScore New SAT score for the student.
     * @return A message indicating the success or failure of the update.
     */
    String updateScore(String name, int newScore);

    /**
     * Deletes the record of a student from the system.
     *
     * @param name Name of the student.
     * @return A message indicating the success or failure of the deletion.
     */
    String deleteRecord(String name);
}
