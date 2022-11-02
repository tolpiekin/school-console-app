package ua.com.foxminded.volodymyrtolpiekin.school.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCourseDao extends JpaRepository<Course, Integer> {

    Optional<Course> findById(int id);

    Optional<Course> findByName(String name);

    List<Course> getAll();

    Optional<Course> addCourse(Course course);

    Optional<Course> updateCourse(Course course);
}
