package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import lombok.extern.log4j.Log4j2;
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

@Log4j2
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
            log.error("Cannot find student with id " + id, e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Student> findByName(String lastName) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_STUDENTS_FIND_BY_LAST_NAME, studentRowMapper, lastName));
        } catch (EmptyResultDataAccessException e) {
            log.error("Cannot find student with lastName " + lastName, e);
            return Optional.empty();
        }
    }

    @Override
    public List<Student> getAll(){
        return jdbcTemplate.query(SQL_STUDENTS_GET_ALL, studentRowMapper);
    }

    @Override
    public Optional<Student> addItem(Student student){
        if (jdbcTemplate.update(SQL_STUDENTS_INSERT, student.getId(), student.getGroupId(), student.getFirstName(),
                student.getLastName()) > 0) {
            return findById(student.getId());
        }
        log.error("Cannot add student id " + student.getId());
        return Optional.empty();
    }

    @Override
    public Optional<Student> updateItem(Student student){
        if (jdbcTemplate.update(SQL_STUDENTS_UPDATE, student.getGroupId(), student.getFirstName(), student.getLastName(),
                student.getId()) > 0) {
            return findById(student.getId());
        }
        log.error("Cannot update sudent with id " + student.getId());
        return Optional.empty();
    }

    @Override
    public void deleteById(int id) {
        if (jdbcTemplate.update(SQL_STUDENTS_DELETE, id) <= 0) {
            log.error("Cannot delete student with id " + id);
        }
    }
}
