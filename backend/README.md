# MediConnect Complete Backend

## Requirements
Java 21, Maven 3.9.x, MongoDB running on localhost:27017.

## Run
Open terminal in the backend folder:

mvn clean
mvn spring-boot:run

Health:
http://localhost:8080/api/health

Doctors:
GET http://localhost:8080/api/doctors

Database:
mediconnect

Collections:
users
appointments
doctors

## Add a doctor in Postman

POST http://localhost:8080/api/doctors

Body -> raw -> JSON:

{
  "name": "Dr. Rahul Kumar",
  "department": "Cardiology",
  "specialization": "Cardiologist",
  "rating": 4.9,
  "experience": 10,
  "available": true
}
