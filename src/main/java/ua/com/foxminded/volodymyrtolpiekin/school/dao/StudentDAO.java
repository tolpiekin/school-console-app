package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.com.foxminded.volodymyrtolpiekin.school.Constants.*;

public class StudentDAO implements DAO<Student> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseDAO.class);
    private final Connection CONNECTION = ConnectionFactory.getInstance().getConnection();
    ResultSet resultSet = null;

    @Override
    public Optional<Student> findById(int id) {
        Student student = null;

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_STUDENTS_FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            student = new Student(resultSet.getInt("student_id"), resultSet.getInt("group_id"),
                    resultSet.getString("first_name"), resultSet.getString("last_name"));
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        }

        return Optional.ofNullable(student);
    }

    @Override
    public Optional<Student> findByName(String lastName) {
        Student student = null;

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_STUDENTS_FIND_BY_LAST_NAME)) {
            preparedStatement.setString(1, lastName);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            student = new Student(resultSet.getInt("student_id"), resultSet.getInt("group_id"),
                    resultSet.getString("first_name"), resultSet.getString("last_name"));
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        }

        return Optional.ofNullable(student);
    }

    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_STUDENTS_GET_ALL)){
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getInt("student_id"),
                        resultSet.getInt("group_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")));
            }
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        }

        return students;
    }

    @Override
    public Optional<Student> addItem(Student student) {
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_STUDENTS_GET_ALL)){
            preparedStatement.setInt(1, student.getGroupId());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        }

        return findById(student.getId());
    }

    @Override
    public Optional<Student> updateItem(Student student) {
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_STUDENTS_GET_ALL)){
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setInt(4, student.getId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        }

        return findById(student.getId());
    }

    @Override
    public void deleteById(int id) {
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_STUDENTS_GET_ALL)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        }
    }
}
