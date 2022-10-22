package ua.com.foxminded.volodymyrtolpiekin.school;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql({ "/drop_schema.sql", "/create_schema.sql" })
@Sql(scripts = "/insert_data1.sql", statements = "insert into students (student_id, group_id, first_name, last_name) values (100, 211, 'John', 'Shiva')")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class SqlTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void fetchRows1() {
        List<Map<String, Object>> students = jdbcTemplate.queryForList("SELECT * FROM students");
        assertEquals(3, students.size());
    }

    @Sql("/insert_more_data1.sql")
    @Test
    void fetchRows2() {
        List<Map<String, Object>> students = jdbcTemplate.queryForList("SELECT * FROM students");
        assertEquals(5, students.size());
    }
}
