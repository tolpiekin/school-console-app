package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.jpa.JpaStudentDao;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final JpaStudentDao jpaStudentDao;

    @Autowired
    public StudentServiceImpl (JpaStudentDao jpaStudentDao) {
        this.jpaStudentDao = jpaStudentDao;
    }

    @Override
    public Optional<Student> findById(int id) {
        return jpaStudentDao.findById(id);
    }

    @Override
    public Optional<Student> findByLastName(String lastName) {
        return jpaStudentDao.findByLastName(lastName);
    }

    @Override
    public List<Student> getAll() {
        return jpaStudentDao.getAll();
    }

    @Override
    public Optional<Student> addStudent(Student student) {
        return jpaStudentDao.addStudent(student);
    }

    @Override
    public Optional<Student> updateStudent(Student student) {
        return jpaStudentDao.updateStudent(student);
    }

    @Override
    public void delStudent(int id){
        jpaStudentDao.deleteById(id);
    }
}
