package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.mappers.CourseRowMapper;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;

import java.util.List;
import java.util.Optional;

import static ua.com.foxminded.volodymyrtolpiekin.school.Constants.*;

@Repository
@Log4j2
public class CourseDAOImpl implements CourseDAO {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Course> courseRowMapper = new CourseRowMapper();

    @Autowired
    public CourseDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Course> findById(int id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_COURSES_FIND_BY_ID, courseRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            log.error("Course not found", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Course> findByName(String name) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_COURSES_FIND_BY_NAME, courseRowMapper, name));
        } catch (EmptyResultDataAccessException e) {
            log.error("Cannot get course by name", e);
            return Optional.empty();
        }
    }

    @Override
    public List<Course> getAll(){
        return jdbcTemplate.query(SQL_COURSES_GET_ALL, courseRowMapper);
    }

    @Override
    public Optional<Course> addItem(Course course){
        if (jdbcTemplate.update(SQL_COURSES_INSERT, course.getId(), course.getName(), course.getDescription()) > 0) {
            return findById(course.getId());
        }
        log.error("Cannot add course " + course.getId());
        return Optional.empty();
    }

    @Override
    public Optional<Course> updateItem(Course course){
        if (jdbcTemplate.update(SQL_COURSES_UPDATE, course.getName(), course.getDescription(), course.getId()) > 0) {
            return findById(course.getId());
        }
        log.error("Cannot update course "+ course.getId());
        return Optional.empty();
    }

    @Override
    public void deleteById(int id) {
        if (jdbcTemplate.update(SQL_COURSES_DELETE, id) <= 0) {
            log.error("Cannot delete course " + id);
        }
    }
}
