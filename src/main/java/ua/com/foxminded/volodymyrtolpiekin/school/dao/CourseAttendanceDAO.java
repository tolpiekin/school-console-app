package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;

public interface CourseAttendanceDAO {

    List<Student> getStudentsByCourseName(String courseName);

    List<Course> getCoursesByStudentId(int studentId);

    boolean addStudentToCourse(int studentId, int courseId);

    boolean isStudentAtCourse(int studentId, int courseId);

    boolean removeStudentFromCourse(int studentId, int courseId);
}
