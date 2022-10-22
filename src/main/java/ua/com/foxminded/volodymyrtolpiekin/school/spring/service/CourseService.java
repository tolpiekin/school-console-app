package ua.com.foxminded.volodymyrtolpiekin.school.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.spring.jdbc.JdbcCourseDAO;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private JdbcCourseDAO jdbcCourseDAO;

    public Optional<Course> findById(int id) {
        return jdbcCourseDAO.findById(id);
    }

    public Optional<Course> findByName(String name) {
        return jdbcCourseDAO.findByName(name);
    }

    public List<Course> getAll() {
        return jdbcCourseDAO.getAll();
    }

    public Optional<Course> addCourse(Course course) {
        return jdbcCourseDAO.addItem(course);
    }

    public Optional<Course> updateCourse(Course course) {
        return jdbcCourseDAO.updateItem(course);
    }

    public void delCourse(int id){
        jdbcCourseDAO.deleteById(id);
    }
}
