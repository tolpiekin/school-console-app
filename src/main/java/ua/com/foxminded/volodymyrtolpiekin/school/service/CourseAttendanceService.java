package ua.com.foxminded.volodymyrtolpiekin.school.service;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;

public interface CourseAttendanceService {
    List<Student> getStudentsAtCourse(String courseName);

    List<Course> getCoursesOfStudent(Student student);

    void addStudentToCourse(Student student, Course course);

    boolean ifStudentAtCourse(Student student, Course course);

    void removeStudentFromCourse(Student student, Course course);
}
