package ua.com.foxminded.volodymyrtolpiekin.school.service;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Optional<Course> findById(int id);

    Optional<Course> findByName(String courseName);

    Optional<Course> addCourse(Course course);

    List<Course> getAll();

    void deleteById(int id);
}
