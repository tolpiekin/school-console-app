package ua.com.foxminded.volodymyrtolpiekin.school.jdbc;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();

        course.setId(rs.getInt("course_id"));
        course.setName(rs.getString("course_name"));
        course.setDescription((rs.getString("course_description")));

        return course;
    }
}
