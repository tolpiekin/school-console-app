package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;

public interface CourseAttendanceDAO {

    List<Student> getCourseStudents(String courseName);

    boolean addStudentToCourse(int studentId, int courseId);

    boolean ifStudentAtCourse(int studentId, int courseId);

    boolean removeStudentFromCourse(int studentId, int courseId);
}
