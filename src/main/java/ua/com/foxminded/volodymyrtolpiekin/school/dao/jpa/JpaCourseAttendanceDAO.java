package ua.com.foxminded.volodymyrtolpiekin.school.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.CourseAttendance;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;

@Repository
public interface JpaCourseAttendanceDAO extends JpaRepository<CourseAttendance, Integer> {

    List<Course> findCoursesByStudent(int studentId);
    List<Student> findStudentsByCourse(int courseId);
    void addStudentToCourse(int studentId, int courseId);
    void removeStudentFromCourse(int studentId, int courseId);
}
