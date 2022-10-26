package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.StudentDAOImpl;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentDAOImpl studentDAOImpl;

    @Autowired
    public StudentServiceImpl (StudentDAOImpl studentDAOImpl) {
        this.studentDAOImpl = studentDAOImpl;
    }

    @Override
    public Optional<Student> findById(int id) {
        return studentDAOImpl.findById(id);
    }

    @Override
    public Optional<Student> findByLastName(String lastName) {
        return studentDAOImpl.findByName(lastName);
    }

    @Override
    public List<Student> getAll() {
        return studentDAOImpl.getAll();
    }

    @Override
    public Optional<Student> addStudent(Student student) {
        return studentDAOImpl.addItem(student);
    }

    @Override
    public Optional<Student> updateStudent(Student student) {
        return studentDAOImpl.updateItem(student);
    }

    @Override
    public void delStudent(int id){
        studentDAOImpl.deleteById(id);
    }
}
