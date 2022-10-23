package ua.com.foxminded.volodymyrtolpiekin.school.daojdbc;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.com.foxminded.volodymyrtolpiekin.school.Constants.*;

public class CourseDAOJDBC implements DAOJDBC<Course> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseDAOJDBC.class);
    private final Connection CONNECTION = ConnectionFactory.getInstance().getConnection();
    ResultSet resultSet = null;

    @Override
    public Optional<Course> findById(int id) {
        Course course = null;

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_COURSES_FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            course = new Course(resultSet.getInt("course_id"), resultSet.getString("course_name"),
                    resultSet.getString("course_description"));
        } catch (SQLException e) {
            LOGGER.error(DATABASE_CONNECTION_CREATION_FAILED, e);
        }

        return Optional.ofNullable(course);
    }

    @Override
    public Optional<Course> findByName(String name) {
        Course course = null;

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_COURSES_FIND_BY_NAME)){
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            course = new Course(resultSet.getInt("course_id"), resultSet.getString("course_name"),
                    resultSet.getString("course_description"));
        } catch (SQLException e) {
            LOGGER.error(DATABASE_CONNECTION_CREATION_FAILED, e);
        }

        return Optional.ofNullable(course);
    }

    @Override
    public List<Course> getAll() {
        List<Course> courses = new ArrayList<>();

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_COURSES_GET_ALL)) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courses.add(new Course(resultSet.getInt("course_id"),
                        resultSet.getString("course_name"),
                        resultSet.getString("course_description")));
            }
        } catch (SQLException e) {
            LOGGER.error(DATABASE_CONNECTION_CREATION_FAILED, e);
        }

        return courses;
    }

    @Override
    public Optional<Course> addItem(Course course) {
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_COURSES_INSERT,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, course.getId());
            preparedStatement.setString(2, course.getName());
            preparedStatement.setString(3, course.getDescription());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            LOGGER.error(DATABASE_CONNECTION_CREATION_FAILED, e);
        }

        return findById(course.getId());
    }

    @Override
    public Optional<Course> updateItem(Course course) {
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_COURSES_UPDATE,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, course.getName());
            preparedStatement.setString(2, course.getDescription());
            preparedStatement.setInt(3, course.getId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            LOGGER.error(DATABASE_CONNECTION_CREATION_FAILED, e);
        }

        return findById(course.getId());
    }

    @Override
    public void deleteById(int id) {
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_COURSES_DELETE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(DATABASE_CONNECTION_CREATION_FAILED, e);
        }
    }
}
