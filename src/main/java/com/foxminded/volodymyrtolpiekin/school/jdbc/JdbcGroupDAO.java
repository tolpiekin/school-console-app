package com.foxminded.volodymyrtolpiekin.school.jdbc;

import com.foxminded.volodymyrtolpiekin.school.dao.GroupDAO;
import com.foxminded.volodymyrtolpiekin.school.models.Group;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import static com.foxminded.volodymyrtolpiekin.school.Constants.*;

@Repository
public class JdbcGroupDAO extends GroupDAO {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Group> groupRowMapper = new GroupRowMapper();

    public JdbcGroupDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Group> findByName(String name) {
        try {
            return Optional.of(jdbcTemplate
                    .queryForObject(SQL_GROUPS_FIND_BY_NAME, groupRowMapper, name));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Group> getById(int id) {
        try {
            return Optional.of(jdbcTemplate
                    .queryForObject(SQL_GROUPS_FIND_BY_ID, groupRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Group> getAll(){
        return jdbcTemplate.query(SQL_COURSES_GET_ALL, groupRowMapper);
    }

    @Override
    public void create(Group group){
        jdbcTemplate.update(SQL_GROUPS_INSERT, group.getId(), group.getName());
    }

    @Override
    public void update(Group group){
        jdbcTemplate.update(SQL_GROUPS_UPDATE, group.getName(), group.getId());
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update(SQL_GROUPS_DELETE, id);
    }
}
