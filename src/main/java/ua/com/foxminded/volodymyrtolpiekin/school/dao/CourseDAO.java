package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ua.com.foxminded.volodymyrtolpiekin.school.Constants.*;

public class CourseDAO implements DAO<Course>{
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseDAO.class);
    private final Connection CONNECTION = ConnectionFactory.getInstance().getConnection();
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    @Override
    public Optional<Course> getById(int id) {
        Course course = null;
        try {
            ptmt = CONNECTION.prepareStatement(SQL_COURSES_FIND_BY_ID);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            resultSet.next();
            course = new Course(resultSet.getInt("course_id"), resultSet.getString("course_name"), resultSet.getString("course_description"));
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        } finally {
            try {
                if (ptmt != null) ptmt.close();
            } catch (Exception e) {
                LOGGER.error("Database Connection Creation Failed : %s", e);
            }
        }
        return Optional.ofNullable(course);
    }

    @Override
    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();
        try {
            ptmt = CONNECTION.prepareStatement(SQL_COURSES_GET_ALL);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                courses.add(new Course(resultSet.getInt("course_id"), resultSet.getString("course_name"), resultSet.getString("course_description")));
            }
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (ptmt != null) ptmt.close();
            } catch (Exception e) {
                LOGGER.error("Database Connection Creation Failed : %s", e);
            }
        }
        return courses;
    }

    @Override
    public void create(Course course) {
        try {
            ptmt = CONNECTION.prepareStatement(SQL_COURSES_INSERT, Statement.RETURN_GENERATED_KEYS);
            ptmt.setInt(1, course.getId());
            ptmt.setString(2, course.getName());
            ptmt.setString(3, course.getDescription());
            ptmt.executeUpdate();
            resultSet = ptmt.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        } finally {
            try {
                if (ptmt != null) ptmt.close();
            } catch (Exception e) {
                LOGGER.error("Cannot close ptmt : %s", e);
            }
        }
    }

    @Override
    public void update(Course course) {
        try {
            ptmt = CONNECTION.prepareStatement(SQL_COURSES_UPDATE, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1, course.getName());
            ptmt.setString(2, course.getDescription());
            ptmt.setInt(3, course.getId());
            ptmt.executeUpdate();
            resultSet = ptmt.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        } finally {
            try {
                if (ptmt != null) ptmt.close();
            } catch (Exception e) {
                LOGGER.error("Cannot close ptmt : %s", e);
            }
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            ptmt = CONNECTION.prepareStatement(SQL_COURSES_DELETE);
            ptmt.setInt(1, id);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        } finally {
            try {
                if (ptmt != null) ptmt.close();
            } catch (Exception e) {
                LOGGER.error("Cannot close ptmt : %s", e);
            }
        }
    }

    @Override
    public Optional<Course> findByName(String name) {
        Course course = null;
        try {
            ptmt = CONNECTION.prepareStatement(SQL_COURSES_FIND_BY_NAME);
            ptmt.setString(1, name);
            resultSet = ptmt.executeQuery();
            resultSet.next();
            course = new Course(resultSet.getInt("course_id"), resultSet.getString("course_name"), resultSet.getString("course_description"));
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        } finally {
            try {
                if (ptmt != null) ptmt.close();
            } catch (Exception e) {
                LOGGER.error("Database Connection Creation Failed : %s", e);
            }
        }
        return Optional.ofNullable(course);
    }
}
