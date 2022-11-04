package ua.com.foxminded.volodymyrtolpiekin.school.service;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;

public interface CourseAttendanceService {
    List<Student> findStudentsByCourseName(String courseName);

    List<Course> findCoursesByStudent(int studentId);

    void addStudentToCourse(int studentId, int courseId);

    boolean ifStudentAtCourse(int studentId, int courseId);

    void delete (int studentId, int courseId);
}
