package ua.com.foxminded.volodymyrtolpiekin.school.service;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;

public interface CourseAttendanceService {
    List<Student> getCourseStudents(String courseName);

    boolean addStudentToCourse(Student student, Course course);

    boolean ifStudentAtCourse(Student student, Course course);

    boolean removeStudentFromCourse(Student student, Course course);

    boolean isTableEmpty ();
}
