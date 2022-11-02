package ua.com.foxminded.volodymyrtolpiekin.school.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface JpaCourseDao extends JpaRepository<Course, Integer> {

    Optional<Course> findById(int id);

    Optional<Course> findByName(String name);

    List<Course> getAll();

    List<Course> findCoursesByStudentId(int studentId);

    Optional<Course> addCourse(Course course);

    Optional<Course> updateCourse(Course course);
}
