Requirement

Create an application (Springboot Backend) with the following features:

- Must have a menu to select option from
1. Insert data
2. View all data
3. Get rank
4. Update score
5. Delete one record
- Insert data - this needs to handle input data for the following Object and store in memory:
SAT Results
- Name (Unique Identifier)
- Address
- City
- Country
- Pincode
- SAT score
- Passed - this needs to be calculated in the backend as follows - if SAT score > 30% = Pass else Fail
- View all data - this should display all the data from the memory in Json format
- Get rank - this takes the name and returns their rank according to the data from the memory
- Update score - this allows to update SAT score for a candidate by name
- Delete one record - this deletes a record by name
- (Optional) Make use of a database of your choice


Introduction
This document outlines the features, technologies used, and configuration details for a Student Management System implemented as a Spring Boot backend application. The system manages SAT results for students, including operations such as inserting data, viewing data, getting ranks, updating scores, and deleting records.

Technologies Used
Java 17
Spring Boot
H2 Database
STS (Spring Tool Suite)
Postman

Configuration
application.properties file
server.port=9080
spring.datasource.url=jdbc:h2:~/data/StudentDetails
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.username=root
spring.datasource.password=root

Url for this h2 database access.
 http://localhost:9080/h2-console
Driver Class=org.h2.Driver
JDBC URL=jdbc:h2:~/data/StudentDetails
User Name=root
Password=root

Config->utility.properties
The utility.properties file enables easy adjustment of parameters like maxSatScore and percentage without modifying the code directly, offering a convenient way to manage configuration settings for future updates, such as if you want to increase these values.
student.management.maxSatScore=100
student.management.percentage=0.3

Database
Student_Details Table
CREATE TABLE Student_Details (
    Name VARCHAR(255) PRIMARY KEY NOT NULL,
    Address VARCHAR(255) NOT NULL,
    City VARCHAR(255) NOT NULL,
    Country VARCHAR(255) NOT NULL,
    Pincode VARCHAR(20) NOT NULL,
    Sat_Score INT NOT NULL,
    Result VARCHAR(20)
);

REST APIs
1. Insert Data
Type of Method: POST
URL: http://localhost:9080/api/studentDetails/insertData

When the API request for inserting student details is successful, the response will indicate "Student Details Inserted Successfully." In cases where the provided data is incomplete or incorrect, the API will generate an error message: "Error inserting student details."
Request Body: Body->raw-JSON
[
    {
        "name": "Akshay",
        "address": "Jp Nagar",
        "city": "Bengaluru",
        "country": "India",
        "pincode": "560078",
        "satScore": 28
    },
    {
        "name": "Deva",
        "address": "Btm Layout",
        "city": "Bengaluru",
        "country": "India",
        "pincode": "560076",
        "satScore": 50
    },
    {
        "name": "Preetham",
        "address": "Hebbala",
        "city": "Bengaluru",
        "country": "India",
        "pincode": "560024",
        "satScore": 20
    },
    {
        "name": "Sanjay",
        "address": "Hebbala",
        "city": "Bengaluru",
        "country": "India",
        "pincode": "560024",
        "satScore": 68
    }
]

2. View All Data
Type of Method: GET
URL: http://localhost:9080/api/studentDetails/viewAll
Upon calling the API, if student details are present in the database, the API will return all the available data. However, if there are no student details in the database table, the response message will be "There is no student details available."
3. Get Rank
Type of Method: GET
URL: http://localhost:9080/api/studentDetails/getRank
Parameters:
Key	Value
name: Sanjay
Upon API request, if the provided name exists in the database, the API will respond with the rank in JSON format, like:
	{
    "rank": 1
}
If the name is not present in the database, the API will return a response indicating: 
Key	Value
name: santhosh
	{
    "rank": "No data available for the name: santhosh"
}

4. Update Score
Type of Method: PUT
URL: http://localhost:9080/api/studentDetails/updateScore
Parameters:
Key	Value
name: Sanjay
newScore: 12
Upon hitting the API, it first checks if the provided name exists in the database. If the name is found, the API updates the newScore for that name and responds with "Score updated successfully for [name]." If the name is not present, an error message is returned: "No data found for name: [name]."

5. Delete One Record
Type of Method: DELETE
URL: http://localhost:9080/api/studentDetails/deleteRecord
Parameters:
Key	Value
name: Sanjay
Upon API request, it initially checks if the provided name exists in the database. If the name is found, the API deletes the corresponding record and responds with "Record deleted successfully for name: [name]." If the name is not present in the database, an error message is returned: "No data found for name: [name]."
