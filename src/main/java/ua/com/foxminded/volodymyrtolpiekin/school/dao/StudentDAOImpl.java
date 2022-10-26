package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;
import ua.com.foxminded.volodymyrtolpiekin.school.mappers.StudentRowMapper;

import java.util.List;
import java.util.Optional;

import static ua.com.foxminded.volodymyrtolpiekin.school.Constants.*;

@Repository
public class StudentDAOImpl implements StudentDAO {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Student> studentRowMapper = new StudentRowMapper();

    @Autowired
    public StudentDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Student> findById(int id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_STUDENTS_FIND_BY_ID, studentRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Student> findByName(String lastName) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_STUDENTS_FIND_BY_LAST_NAME, studentRowMapper, lastName));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Student> getAll(){
        return jdbcTemplate.query(SQL_STUDENTS_GET_ALL, studentRowMapper);
    }

    @Override
    public Optional<Student> addItem(Student student){
        jdbcTemplate.update(SQL_STUDENTS_INSERT, student.getId(), student.getGroupId(), student.getFirstName(),
                student.getLastName());

        return findById(student.getId());
    }

    @Override
    public Optional<Student> updateItem(Student student){
        jdbcTemplate.update(SQL_STUDENTS_UPDATE, student.getGroupId(), student.getFirstName(), student.getLastName(),
                student.getId());

        return findById(student.getId());
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update(SQL_STUDENTS_DELETE, id);
    }
}
