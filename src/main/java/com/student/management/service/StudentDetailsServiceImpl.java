package com.student.management.service;

import java.io.FileInputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.management.entity.StudentDetails;
import com.student.management.repo.StudentDetailsRepository;



@Service
public class StudentDetailsServiceImpl implements StudentDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(StudentDetailsServiceImpl.class);

	@Autowired
	private StudentDetailsRepository studentDetailsRepository;
	
	@Override
	public String insertData(List<StudentDetails> studentDetailsList) {
		// Loading configuration properties from a file
	    final Properties mainProperties = new Properties();
    	try{
    		final String dbPropertiesPath = "./Config/utility.properties";
            final FileInputStream file = new FileInputStream(dbPropertiesPath);
            if (file == null) {
                System.out.println("Please sourcedatabase.properties file with source database configurations is missing.....");
                System.exit(0);
            }
            else {
                mainProperties.load(file);
            }
        }catch (Exception e) {
               return null;	
    	}
    	// Extracting maxSatScore and percentage from properties
    	final String Score = mainProperties.getProperty("student.management.maxSatScore");
    	int maxSatScore =  Integer.parseInt(Score);
    	final String Percent = mainProperties.getProperty("student.management.percentage");
    	double percent1=Double.parseDouble(Percent);
    	
	    try {
	        for (StudentDetails studentDetails : studentDetailsList) {
	            double percentage = percent1 * maxSatScore;
	            studentDetails.setResult(studentDetails.getSatScore() > percentage ? "Pass" : "Fail");
	            studentDetailsRepository.save(studentDetails);
	        }
	        logger.info("Student Details Inserted Successfully.");
	        return "Student Details Inserted Successfully.";
	    } catch (Exception e) {
	    	logger.error("Error inserting in student details.", e);
	        throw new RuntimeException("Error inserting in student details.", e);
	    }
	}
	
	
	@Override
	public List<StudentDetails> getAllData() {
	    try {
	        List<StudentDetails> allData = studentDetailsRepository.findAll();

	        if (allData.isEmpty()) {
	            logger.warn("No student details found.");
	            throw new RuntimeException("There is no student details available");
	        }
	        logger.info("Successfully retrieved all student details.");
	        return allData;
	    } catch (Exception e) {
	        logger.error("Error retrieving all student details.", e);
	        throw new RuntimeException("There is no student details available.");
	    }
	}


	@Override
	public Map<String, Object> getRank(String name) {
	    Map<String, Object> response = new HashMap<>();
	    try {
	        List<StudentDetails> allStudents = studentDetailsRepository.findAll();
	        List<StudentDetails> sortedStudents = allStudents.stream()
	                .sorted(Comparator.comparing(StudentDetails::getSatScore).reversed())
	                .collect(Collectors.toList());

	        for (int i = 0; i < sortedStudents.size(); i++) {
	            if (sortedStudents.get(i).getName().equals(name)) {
	                int rank = i + 1;
	                logger.info("Rank for " + name + " is " + rank);
	                response.put("rank", rank);
	                return response;
	            }
	        }
	        logger.warn("No data available for the name: " + name);
	        response.put("rank", "No data available for the name: "+name);
	        return response;
	    } catch (Exception e) {
	        String errorMessage = "Error getting rank for " + name;
	        logger.error(errorMessage, e);
	        response.put("error", errorMessage);
	        return response;
	    }
	}


    @Override
    public String updateScore(String name, int newScore) {
        try {
            StudentDetails studentDetails = studentDetailsRepository.findById(name).orElse(null);
            if (studentDetails != null) {
                studentDetails.setSatScore(newScore);
                studentDetails.setResult(newScore > 30 ? "Pass" : "Fail");
                studentDetailsRepository.save(studentDetails);

                logger.info("Score updated successfully for " + name);
                return "Score updated successfully for " + name;
            } else {
                logger.warn("No data found for name: " + name);
                return "No data found for name: " + name;
            }
        } catch (Exception e) {
            logger.error("Error updating score for " + name, e);
            return "Error updating score for " + name;
        }
    }
	
	
    @Override
    public String deleteRecord(String name) {
        try {
            if (studentDetailsRepository.existsById(name)) {
                studentDetailsRepository.deleteById(name);
                logger.info("Record deleted successfully for name: " + name);
                return "Record deleted successfully for name: " + name;
            } else {
                logger.warn("No data found for name: " + name);
                return "No data found for name: " + name;
            }
        } catch (Exception e) {
            logger.error("Error deleting record for name: " + name, e);
            throw new RuntimeException("Error deleting record for name: " + name, e);
        }
    }

}

