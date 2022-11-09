package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import ua.com.foxminded.volodymyrtolpiekin.school.models.CourseAttendance;

import java.util.List;
import java.util.Optional;

public interface CourseAttendanceDAO {

    Optional<CourseAttendance> findById(int id);

    List<CourseAttendance> getStudentsByCourseId(int courseId);

    List<CourseAttendance> getCoursesByStudentId(int studentId);

    void addStudentToCourse(CourseAttendance courseAttendance);

    void deleteById(int courseAttendanceId);
}
