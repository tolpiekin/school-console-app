package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.CourseAttendance;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCourseAttendanceDao extends JpaRepository<CourseAttendance, Integer> {

    Optional<List<CourseAttendance>> findByStudentId(int studentId);
    Optional<List<CourseAttendance>> findByCourseId(int courseId);
}
