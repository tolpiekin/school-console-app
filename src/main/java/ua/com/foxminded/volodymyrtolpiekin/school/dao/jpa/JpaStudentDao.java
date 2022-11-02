package ua.com.foxminded.volodymyrtolpiekin.school.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaStudentDao extends JpaRepository<Student, Integer> {

    Optional<Student> findById(int id);

    Optional<Student> findByLastName(String lastName);

    List<Student> getAll();

    Optional<Student> addStudent(Student student);

    Optional<Student> updateStudent(Student student);

    void delStudent(int id);
}
