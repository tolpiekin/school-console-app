package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;
import ua.com.foxminded.volodymyrtolpiekin.school.mappers.GroupRowMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ua.com.foxminded.volodymyrtolpiekin.school.Constants.*;
import static ua.com.foxminded.volodymyrtolpiekin.school.SQLQueries.*;

@Log4j2
@Repository
public class GroupDAOImpl implements GroupDAO {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Group> groupRowMapper = new GroupRowMapper();

    @Autowired
    public GroupDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Group> findById(int id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_GROUPS_FIND_BY_ID, groupRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            log.error(String.format("Group with id: %d not found.", id));
            return Optional.empty();
        }
    }

    @Override
    public Optional<Group> findByName(String name) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_GROUPS_FIND_BY_NAME, groupRowMapper, name));
        } catch (EmptyResultDataAccessException e) {
            log.error(String.format("Group with groupName: %s not found.", name));
            return Optional.empty();
        }
    }

    @Override
    public List<Group> getAll(){
        return jdbcTemplate.query(SQL_GROUPS_GET_ALL, groupRowMapper);
    }

    @Override
    @Transactional
    public Optional<Group> addItem(Group group){
        if (jdbcTemplate.update(SQL_GROUPS_INSERT, group.getId(), group.getName()) > QUERY_RESULT) {
            return findById(group.getId());
        }
        log.error(String.format("Cannot add group with id: %d.", group.getId()));
        return Optional.empty();
    }

    @Override
    public Optional<Group> updateItem(Group group){
        if (jdbcTemplate.update(SQL_GROUPS_UPDATE, group.getName(), group.getId()) > QUERY_RESULT) {
            return findById(group.getId());
        }
        log.error(String.format("Cannot update group with id: %d.", group.getId()));
        return Optional.empty();
    }

    @Override
    public void deleteById(int id) {
        if (jdbcTemplate.update(SQL_GROUPS_DELETE, id) <= QUERY_RESULT) {
            log.error(String.format("Cannot delete group with id: %d.", id));
        }
    }

    public List<Map<String, Object>> findGroupsSmallerThenNumber(int size) {
        return jdbcTemplate.queryForList(SQL_GROUPS_LESS_THEN, size);
    }
}
