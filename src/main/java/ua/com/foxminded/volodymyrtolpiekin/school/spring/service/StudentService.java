package ua.com.foxminded.volodymyrtolpiekin.school.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;
import ua.com.foxminded.volodymyrtolpiekin.school.spring.jdbc.JdbcStudentDAO;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private JdbcStudentDAO jdbcStudentDAO;

    public Optional<Student> findById(int id) {
        return jdbcStudentDAO.findById(id);
    }

    public Optional<Student> findByLastName(String lastName) {
        return jdbcStudentDAO.findByName(lastName);
    }

    public List<Student> getAll() {
        return jdbcStudentDAO.getAll();
    }

    public Optional<Student> addStudent(Student student) {
        return jdbcStudentDAO.addItem(student);
    }

    public Optional<Student> updateStudent(Student student) {
        return jdbcStudentDAO.updateItem(student);
    }

    public void delStudent(int id){
        jdbcStudentDAO.deleteById(id);
    }
}
