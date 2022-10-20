package ua.com.foxminded.volodymyrtolpiekin.school.jdbc;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupRowMapper implements RowMapper<Group> {
    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        Group group = new Group();

        group.setId(rs.getInt("group_id"));
        group.setName(rs.getString("group_name"));

        return group;
    }
}
