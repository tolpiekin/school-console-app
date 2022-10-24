package ua.com.foxminded.volodymyrtolpiekin.school.service;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Optional<Course> findById(int id);

    Optional<Course> findByName(String name);

    List<Course> getAll();

    Optional<Course> addCourse(Course course);

    Optional<Course> updateCourse(Course course);

    void deleteById(int id);

    boolean isTableEmpty ();
}
