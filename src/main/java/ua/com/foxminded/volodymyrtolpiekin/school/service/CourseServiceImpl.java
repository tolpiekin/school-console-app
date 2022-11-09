package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.JCourseDao;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    private final JCourseDao jCourseDao;

    @Autowired
    public CourseServiceImpl (JCourseDao jCourseDao) {
        this.jCourseDao = jCourseDao;
    }

    @Override
    public Optional<Course> findById(int id) {
        return jCourseDao.findById(id);
    }

    @Override
    public Optional<Course> findByName(String name) {
        return jCourseDao.findByName(name);
    }

    @Override
    public List<Course> getAll() {
        return jCourseDao.getAll();
    }

    @Override
    public Optional<Course> addCourse(Course course) {
        return jCourseDao.addItem(course);
    }

    @Override
    public Optional<Course> updateCourse(Course course) {
        return jCourseDao.updateItem(course);
    }

    @Override
    public void deleteById(int id){
        jCourseDao.deleteById(id);
    }
}
