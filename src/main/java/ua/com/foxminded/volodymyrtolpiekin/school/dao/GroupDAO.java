package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.com.foxminded.volodymyrtolpiekin.school.Constants.*;

public class GroupDAO implements DAO<Group> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupDAO.class);
    private final Connection CONNECTION = ConnectionFactory.getInstance().getConnection();
    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    @Override
    public Optional<Group> getById(int id) {
        Group group = null;
        try {
            ptmt = CONNECTION.prepareStatement(SQL_GROUPS_FIND_BY_ID);
            ptmt.setInt(1, id);
            resultSet = ptmt.executeQuery();
            resultSet.next();
            group = new Group(resultSet.getInt("group_id"), resultSet.getString("group_name"));
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (ptmt != null)
                    ptmt.close();
            } catch (Exception e) {
                LOGGER.error("Cannot close ptmt : %s", e);
            }
        }
        return Optional.ofNullable(group);
    }

    @Override
    public Optional<Group> findByName(String name) {
        Group group = null;
        try {
            ptmt = CONNECTION.prepareStatement(SQL_GROUPS_FIND_BY_NAME);
            ptmt.setString(1, name);
            resultSet = ptmt.executeQuery();
            resultSet.next();
            group = new Group(resultSet.getInt("group_id"), resultSet.getString("group_name"));
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (ptmt != null)
                    ptmt.close();
            } catch (Exception e) {
                LOGGER.error("Cannot close ptmt : %s", e);
            }
        }
        return Optional.ofNullable(group);
    }

    @Override
    public List<Group> getAll() {
        List<Group> groups = new ArrayList<>();
        try {
            ptmt = CONNECTION.prepareStatement(SQL_GROUPS_GET_ALL);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                groups.add(new Group(resultSet.getInt("group_id"), resultSet.getString("group_name")));
            }
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (ptmt != null)
                    ptmt.close();
            } catch (Exception e) {
                LOGGER.error("Cannot close ptmt : %s", e);
            }
        }
        return groups;
    }

    @Override
    public void create(Group group) {
        try {
            ptmt = CONNECTION.prepareStatement(SQL_GROUPS_INSERT, Statement.RETURN_GENERATED_KEYS);
            ptmt.setInt(1, group.getId());
            ptmt.setString(2, group.getName());
            ptmt.executeUpdate();
            resultSet = ptmt.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        } finally {
            try {
                if (ptmt != null)
                    ptmt.close();
            } catch (Exception e) {
                LOGGER.error("Cannot close ptmt : %s", e);
            }
        }
    }

    @Override
    public void update(Group group) {
        try {
            ptmt = CONNECTION.prepareStatement(SQL_GROUPS_UPDATE, Statement.RETURN_GENERATED_KEYS);
            ptmt.setString(1, group.getName());
            ptmt.setInt(2, group.getId());
            ptmt.executeUpdate();
            resultSet = ptmt.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        } finally {
            try {
                if (ptmt != null)
                    ptmt.close();
            } catch (Exception e) {
                LOGGER.error("Cannot close ptmt : %s", e);
            }
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            ptmt = CONNECTION.prepareStatement(SQL_GROUPS_DELETE);
            ptmt.setInt(1, id);
            ptmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        } finally {
            try {
                if (ptmt != null)
                    ptmt.close();
            } catch (Exception e) {
                LOGGER.error("Cannot close ptmt : %s", e);
            }
        }
    }
}
