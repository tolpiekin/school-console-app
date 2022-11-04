package ua.com.foxminded.volodymyrtolpiekin.school.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.Optional;

@Repository
public interface JpaStudentDao extends JpaRepository<Student, Integer> {
    Optional<Student> findByLastName(String lastName);
}
