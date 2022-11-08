package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;

import java.util.Optional;

@Repository
public interface JpaCourseDao extends JpaRepository<Course, Integer> {
    Optional<Course> findByCourseName(String courseName);

}
