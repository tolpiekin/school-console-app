package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.StudentDAOImpl;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDAOImpl jdbcStudentDAOImpl;

    @Override
    public Optional<Student> findById(int id) {
        return jdbcStudentDAOImpl.findById(id);
    }

    @Override
    public Optional<Student> findByLastName(String lastName) {
        return jdbcStudentDAOImpl.findByName(lastName);
    }

    @Override
    public List<Student> getAll() {
        return jdbcStudentDAOImpl.getAll();
    }

    @Override
    public Optional<Student> addStudent(Student student) {
        return jdbcStudentDAOImpl.addItem(student);
    }

    @Override
    public Optional<Student> updateStudent(Student student) {
        return jdbcStudentDAOImpl.updateItem(student);
    }

    @Override
    public void delStudent(int id){
        jdbcStudentDAOImpl.deleteById(id);
    }
}
