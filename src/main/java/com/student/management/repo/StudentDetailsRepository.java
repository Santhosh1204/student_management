package com.student.management.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.management.entity.StudentDetails;

//JpaRepository is a Spring Data JPA interface for generic CRUD operations on a repository for a specific type.
//In this case, it is used for StudentDetails entity with String as the type of the primary key.
public interface StudentDetailsRepository extends JpaRepository<StudentDetails, String>{

}
