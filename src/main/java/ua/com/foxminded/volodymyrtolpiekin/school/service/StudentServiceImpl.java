package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.JStudentDao;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final JStudentDao jStudentDao;

    @Autowired
    public StudentServiceImpl (JStudentDao jStudentDao) {
        this.jStudentDao = jStudentDao;
    }

    @Override
    public Optional<Student> findById(int id) {
        return jStudentDao.findById(id);
    }

    @Override
    public Optional<Student> findByLastName(String lastName) {
        return jStudentDao.findByName(lastName);
    }

    @Override
    public List<Student> getAll() {
        return jStudentDao.getAll();
    }

    @Override
    public Optional<Student> addStudent(Student student) {
        return jStudentDao.addItem(student);
    }

    @Override
    public Optional<Student> updateStudent(Student student) {
        return jStudentDao.updateItem(student);
    }

    @Override
    public void delStudent(int id){
        jStudentDao.deleteById(id);
    }
}
