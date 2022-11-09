package ua.com.foxminded.volodymyrtolpiekin.school.service;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;

public interface CourseAttendanceService {
    List<Student> getStudentsAtCourse(String courseName);

    List<Course> getCoursesOfStudent(int studentId);

    void addStudentToCourse(int studentId, int courseId);

    boolean ifStudentAtCourse(int studentId, int courseId);

    void removeStudentFromCourse(int studentId, int courseId);
}
