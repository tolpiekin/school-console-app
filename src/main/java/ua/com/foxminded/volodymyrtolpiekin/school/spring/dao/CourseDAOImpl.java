package ua.com.foxminded.volodymyrtolpiekin.school.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;
import ua.com.foxminded.volodymyrtolpiekin.school.spring.mappers.CourseRowMapper;
import ua.com.foxminded.volodymyrtolpiekin.school.spring.mappers.StudentRowMapper;

import java.util.List;
import java.util.Optional;

import static ua.com.foxminded.volodymyrtolpiekin.school.Constants.*;

@Repository
public class CourseDAOImpl implements CourseDAO {
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Course> courseRowMapper = new CourseRowMapper();
    private final RowMapper<Student> studentRowMapper = new StudentRowMapper();

    public CourseDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Course> findById(int id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_COURSES_FIND_BY_ID, courseRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Course> findByName(String name) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_COURSES_FIND_BY_NAME, courseRowMapper, name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Course> getAll(){
        return jdbcTemplate.query(SQL_COURSES_GET_ALL, courseRowMapper);
    }

    @Override
    public Optional<Course> addItem(Course course){
        jdbcTemplate.update(SQL_COURSES_INSERT, course.getId(), course.getName(), course.getDescription());

        return findById(course.getId());
    }

    @Override
    public Optional<Course> updateItem(Course course){
        jdbcTemplate.update(SQL_COURSES_UPDATE, course.getName(), course.getDescription(), course.getId());

        return findById(course.getId());
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update(SQL_COURSES_DELETE, id);
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
        return jdbcTemplate.update(SQL_COURSES_IF_STUDENT_AT_COURSE, studentId, courseId) > 0;
    }

    @Override
    public boolean removeStudentFromCourse(int studentId, int courseId){
        return jdbcTemplate.update(SQL_COURSES_REMOVE_STUDENT_FROM_COURSE, studentId, courseId) > 0;
    }
}
