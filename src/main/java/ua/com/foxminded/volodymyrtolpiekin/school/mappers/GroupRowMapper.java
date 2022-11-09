package ua.com.foxminded.volodymyrtolpiekin.school.mappers;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupRowMapper implements RowMapper<Group> {
    @Override
    public Group mapRow(ResultSet rs, int rowNum) throws SQLException {
        Group group = new Group();

        group.setGroupId(rs.getInt("group_id"));
        group.setGroupName(rs.getString("group_name"));

        return group;
    }
}
