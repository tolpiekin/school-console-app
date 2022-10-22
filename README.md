Task 2.3 Service layer
Assignment:
Create a service layer on the top of your DAO to implement the following requirements:

Find all groups with less or equals student count

Find all students related to the course with the given name

Add a new student

Delete a student by STUDENT_ID

Add a student to the course that is going to exit the course

Remove the student from one of their courses

** Add missing DAO methods to accomplish services needs

Create a generator service that will be called if database is empty:

Create 10 groups with randomly generated names. The name should contain 2 characters, hyphen, 2 numbers

Create 10 courses (math, biology, etc.)

Create 200 students. Take 20 first names and 20 last names and randomly combine them to generate students.

Randomly assign students to the groups. Each group can contain from 10 to 30 students. It is possible that some groups are without students or students without groups

Create the relation MANY-TO-MANY between the tables STUDENTS and COURSES. Randomly assign from 1 to 3 courses for each student

Hint:

Use ApplicationRunner interface as an entry point for triggering generator

Important

Cover your services with tests using mocked DAO layer