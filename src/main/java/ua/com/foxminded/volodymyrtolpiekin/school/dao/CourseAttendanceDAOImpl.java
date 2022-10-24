package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.mappers.StudentRowMapper;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;

import static ua.com.foxminded.volodymyrtolpiekin.school.Constants.*;

@Repository
public class CourseAttendanceDAOImpl implements CourseAttendanceDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Student> studentRowMapper = new StudentRowMapper();

    public CourseAttendanceDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> getCourseStudents(String courseName) {
        return jdbcTemplate.query(SQL_COURSES_GET_STUDENTS_BY_COURSE, studentRowMapper, courseName);
    }

    @Override
    public boolean addStudentToCourse(int studentId, int courseId) {
        return jdbcTemplate.update(SQL_COURSES_ADD_STUDENT_TO_COURSE, studentId, courseId) > 0;
    }

    @Override
    public boolean ifStudentAtCourse(int studentId, int courseId){
        return jdbcTemplate.queryForObject(SQL_COURSES_IF_STUDENT_AT_COURSE, new Object[] { studentId, courseId},
                Boolean.class );
    }

    @Override
    public boolean removeStudentFromCourse(int studentId, int courseId){
        return jdbcTemplate.update(SQL_COURSES_REMOVE_STUDENT_FROM_COURSE, studentId, courseId) > 0;
    }

    @Override
    public boolean isTableEmpty(){
        return jdbcTemplate.queryForObject(SQL_COURSES_NOT_EMPTY, Integer.class) == 0;
    }
}
