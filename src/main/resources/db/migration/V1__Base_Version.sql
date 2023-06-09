CREATE TABLE groups (
  group_id SERIAL,
  group_name varchar(5) NOT NULL,
  CONSTRAINT "groups.pkey" PRIMARY KEY (group_id)
);

CREATE TABLE students (
  student_id SERIAL,
  group_id integer NOT NULL,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  CONSTRAINT "students.pkey" PRIMARY KEY (student_id)
);

CREATE TABLE courses (
  course_id SERIAL,
  course_name varchar(50) NOT NULL,
  course_description varchar(200) NOT NULL,
  CONSTRAINT "courses.pkey" PRIMARY KEY (course_id)
);

CREATE TABLE course_attendance (
    student_id integer NOT NULL,
    course_id integer NOT NULL,
    CONSTRAINT "course_attendance.pkey" PRIMARY KEY (student_id, course_id)
    );