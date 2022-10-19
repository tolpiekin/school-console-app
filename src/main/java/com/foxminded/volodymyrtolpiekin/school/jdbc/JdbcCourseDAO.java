package com.foxminded.volodymyrtolpiekin.school.jdbc;

import com.foxminded.volodymyrtolpiekin.school.dao.CourseDAO;
import com.foxminded.volodymyrtolpiekin.school.models.Course;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;

public class JdbcCourseDAO extends CourseDAO {

    public static final String FIND_BY_NAME = "select * from courses where course_name = ?";

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Course> courseRowMapper = new CourseRowMapper();

    public JdbcCourseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    Optional<Course> findByName(String name) {
        try {
            return Optional.of(jdbcTemplate
                    .queryForObject(FIND_BY_NAME, courseRowMapper, name))
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    // rest of code
}
