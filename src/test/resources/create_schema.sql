CREATE TABLE students (
  student_id integer NOT NULL,
  group_id integer NOT NULL,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  CONSTRAINT "students.pkey" PRIMARY KEY (student_id)
);