package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
        try {
            return Optional.of(jStudentDao.findById(id).orElseThrow(() ->
                    new StudentNotFoundException(id)
            ));
        }
        catch (StudentNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not found", exc);
        }

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
        Optional<Student> returnedStudent;
        try {
            returnedStudent = Optional.of(jStudentDao.findById(id).orElseThrow(() ->
                    new StudentNotFoundException(id)));
        }
        catch (StudentNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found", exc);
        }

        jStudentDao.deleteById(returnedStudent.get().getStudentId());
    }
}
