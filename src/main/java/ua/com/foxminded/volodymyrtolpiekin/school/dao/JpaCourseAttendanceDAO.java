package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.CourseAttendance;

import java.util.List;

@Repository
public interface JpaCourseAttendanceDAO extends JpaRepository<CourseAttendance, Integer> {

    List<CourseAttendance> findCoursesByStudentId(int studentId);
    List<CourseAttendance> findStudentsByCourseId(int courseId);

}
