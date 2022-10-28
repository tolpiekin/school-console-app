package ua.com.foxminded.volodymyrtolpiekin.school.service;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;
import java.util.Map;

public interface CourseAttendanceService {
    List<Student> getStudentsAtCourse(String courseName);

    List<Course> getCoursesOfStudent(int studentId);

    boolean addStudentToCourse(int studentId, int courseId);

    boolean ifStudentAtCourse(int studentId, int courseId);

    boolean removeStudentFromCourse(int studentId, int courseId);
}
