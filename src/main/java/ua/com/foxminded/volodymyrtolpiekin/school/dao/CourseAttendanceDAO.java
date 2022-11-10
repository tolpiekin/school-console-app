package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.CourseAttendance;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;
import java.util.Optional;

public interface CourseAttendanceDAO {

    Optional<CourseAttendance> findById(int id);

    List<CourseAttendance> getByCourseId(Course course);

    List<CourseAttendance> getByStudent(Student student);

    void addStudentToCourse(CourseAttendance courseAttendance);

    void deleteById(int courseAttendanceId);
}
