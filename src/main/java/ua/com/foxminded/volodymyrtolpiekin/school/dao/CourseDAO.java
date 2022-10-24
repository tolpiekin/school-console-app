package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDAO {
    Optional<Course> findById(int id);

    Optional<Course> findByName(String name);

    List<Course> getAll();

    Optional<Course> addItem(Course course);

    Optional<Course> updateItem(Course course);

    void deleteById(int id);

    boolean isTableEmpty();
}
