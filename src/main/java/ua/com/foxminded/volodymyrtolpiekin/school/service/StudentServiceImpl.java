package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.JpaStudentDao;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final JpaStudentDao jpaStudentDao;

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
        return jpaStudentDao.findAll();
    }

    @Override
    public Optional<Student> addStudent(Student student) {
        jpaStudentDao.save(student);
        return jpaStudentDao.findById(student.getId());
    }

    @Override
    public Optional<Student> updateStudent(Student student, int id) {
        student.setId(id);
        jpaStudentDao.save(student);
        return jpaStudentDao.findById(student.getId());
    }

    @Override
    public void delStudent(int id){
        jpaStudentDao.deleteById(id);
    }
}
