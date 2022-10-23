package ua.com.foxminded.volodymyrtolpiekin.school.spring.dao;

import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;
import java.util.Optional;

public interface CourseDAO {
    Optional<Course> findById(int id);

    Optional<Course> findByName(String name);

    List<Course> getAll();

    Optional<Course> addItem(Course course);

    Optional<Course> updateItem(Course course);

    void deleteById(int id);

    List<Student> getCourseStudents(String courseName);

    boolean addStudentToCourse(int studentId, int courseId);

    boolean ifStudentAtCourse(int studentId, int courseId);

    boolean removeStudentFromCourse(int studentId, int courseId);
}
