package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDAO {
    Optional<Student> findById(int id);

    Optional<Student> findByName(String name);

    List<Student> getAll();

    Optional<Student> addItem(Student student);

    Optional<Student> updateItem(Student student);

    void deleteById(int id);

    boolean isTableEmpty();
}
