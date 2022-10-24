package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.CourseDAOImpl;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseDAOImpl courseDAOImpl;

    @Override
    public Optional<Course> findById(int id) {
        return courseDAOImpl.findById(id);
    }

    @Override
    public Optional<Course> findByName(String name) {
        return courseDAOImpl.findByName(name);
    }

    @Override
    public List<Course> getAll() {
        return courseDAOImpl.getAll();
    }

    @Override
    public Optional<Course> addCourse(Course course) {
        return courseDAOImpl.addItem(course);
    }

    @Override
    public Optional<Course> updateCourse(Course course) {
        return courseDAOImpl.updateItem(course);
    }

    @Override
    public void deleteById(int id){
        courseDAOImpl.deleteById(id);
    }

    @Override
    public boolean isTableEmpty() {
        return courseDAOImpl.isTableEmpty();
    }
}
