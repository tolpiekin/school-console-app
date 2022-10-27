package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;

import java.util.List;
import java.util.Map;

public interface CourseAttendanceDAO {

    List<Map<String, Object>> getStudentsByCourseName(String courseName);

    List<Course> getCoursesByStudentId(int studentId);

    boolean addStudentToCourse(int studentId, int courseId);

    boolean isStudentAtCourse(int studentId, int courseId);

    boolean removeStudentFromCourse(int studentId, int courseId);
}
