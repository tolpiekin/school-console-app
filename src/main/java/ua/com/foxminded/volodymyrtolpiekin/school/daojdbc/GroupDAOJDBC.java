package ua.com.foxminded.volodymyrtolpiekin.school.daojdbc;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.com.foxminded.volodymyrtolpiekin.school.Constants.*;

public class GroupDAOJDBC implements DAOJDBC<Group> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupDAOJDBC.class);
    private final Connection CONNECTION = ConnectionFactory.getInstance().getConnection();
    ResultSet resultSet = null;

    @Override
    public Optional<Group> findById(int id) {
        Group group = null;

        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_GROUPS_FIND_BY_ID)){
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            group = new Group(resultSet.getInt("group_id"), resultSet.getString("group_name"));
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        }

        return Optional.ofNullable(group);
    }

    @Override
    public Optional<Group> findByName(String name) {
        Group group = null;
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_GROUPS_FIND_BY_NAME)){
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            group = new Group(resultSet.getInt("group_id"), resultSet.getString("group_name"));
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        }

        return Optional.ofNullable(group);
    }

    @Override
    public List<Group> getAll() {
        List<Group> groups = new ArrayList<>();
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_GROUPS_GET_ALL)){
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                groups.add(new Group(resultSet.getInt("group_id"), resultSet.getString("group_name")));
            }
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        }

        return groups;
    }

    @Override
    public Optional<Group> addItem(Group group) {
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_GROUPS_INSERT,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, group.getId());
            preparedStatement.setString(2, group.getName());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        }

        return findById(group.getId());
    }

    @Override
    public Optional<Group> updateItem(Group group) {
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_GROUPS_UPDATE,
                Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, group.getName());
            preparedStatement.setInt(2, group.getId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        }

        return findById(group.getId());
    }

    @Override
    public void deleteById(int id) {
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(SQL_GROUPS_DELETE)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        }
    }
}
