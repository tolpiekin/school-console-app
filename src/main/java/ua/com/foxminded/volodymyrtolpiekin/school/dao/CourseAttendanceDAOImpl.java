package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.mappers.CourseRowMapper;
import ua.com.foxminded.volodymyrtolpiekin.school.mappers.StudentRowMapper;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;

import static ua.com.foxminded.volodymyrtolpiekin.school.Constants.*;

@Repository
@Log4j2
public class CourseAttendanceDAOImpl implements CourseAttendanceDAO {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Student> studentRowMapper = new StudentRowMapper();
    private final RowMapper<Course> courseRowMapper = new CourseRowMapper();

    @Autowired
    public CourseAttendanceDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> getStudentsByCourseName(String courseName) {
        return jdbcTemplate.query(String.format(SQL_COURSES_GET_STUDENTS_BY_COURSE, courseName), studentRowMapper);
    }

    @Override
    public List<Course> getCoursesByStudentId(int studentId) {
        return jdbcTemplate.query(SQL_COURSES_GET_COURSES_OF_STUDENT, courseRowMapper, studentId);
    }

    @Override
    public boolean addStudentToCourse(int studentId, int courseId) {
        if (isStudentAtCourse(studentId, courseId)) {
            log.error(String.format("Student %d already at course %d.", studentId, courseId));
            return false;
        }
        return jdbcTemplate.update(SQL_COURSES_ADD_STUDENT_TO_COURSE, studentId, courseId) > ZERO;
    }

    @Override
    public boolean isStudentAtCourse(int studentId, int courseId){
        return jdbcTemplate.queryForObject(SQL_COURSES_IF_STUDENT_AT_COURSE, new Object[] { studentId, courseId},
                Boolean.class );
    }

    @Override
    public boolean removeStudentFromCourse(int studentId, int courseId){
        if (isStudentAtCourse(studentId, courseId)){
            return jdbcTemplate.update(SQL_COURSES_REMOVE_STUDENT_FROM_COURSE, studentId, courseId) > ZERO;
        }
        log.error(String.format("Student %d not attending course %d", studentId, courseId));
        return false;
    }
}
