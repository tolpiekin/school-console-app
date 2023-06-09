package ua.com.foxminded.volodymyrtolpiekin.school.service;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<Student> findById(int id) throws StudentNotFoundException;

    Optional<Student> findByLastName(String lastName);

    List<Student> getAll();

    Optional<Student> addStudent(Student student);

    Optional<Student> updateStudent(Student student, int id);

    void delStudent(int id) throws StudentNotFoundException;
}
