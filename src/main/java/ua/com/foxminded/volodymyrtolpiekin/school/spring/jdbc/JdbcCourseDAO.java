package ua.com.foxminded.volodymyrtolpiekin.school.spring.jdbc;

import ua.com.foxminded.volodymyrtolpiekin.school.dao.CourseDAO;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import static ua.com.foxminded.volodymyrtolpiekin.school.Constants.*;

@Repository
public class JdbcCourseDAO extends CourseDAO {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Course> courseRowMapper = new CourseRowMapper();

    public JdbcCourseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Course> findById(int id) {
        try {
            return Optional.of(jdbcTemplate
                    .queryForObject(SQL_COURSES_FIND_BY_ID, courseRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Course> findByName(String name) {
        try {
            return Optional.of(jdbcTemplate
                    .queryForObject(SQL_COURSES_FIND_BY_NAME, courseRowMapper, name));
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
}
