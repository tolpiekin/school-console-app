package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.jpa.JpaCourseDao;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    private final JpaCourseDao jpaCourseDao;

    public CourseServiceImpl (JpaCourseDao jpaCourseDao) {
        this.jpaCourseDao = jpaCourseDao;
    }

    @Override
    public Optional<Course> findById(int id) {
        return jpaCourseDao.findById(id);
    }

    @Override
    public Optional<Course> findByName(String name) {
        return jpaCourseDao.findByName(name);
    }

    @Override
    public Optional<Course> addCourse(Course course) {
        jpaCourseDao.save(course);
        return jpaCourseDao.findById(course.getId());
    }

    @Override
    public List<Course> getAll() {
        return jpaCourseDao.findAll();
    }

    @Override
    public void deleteById(int id){
        jpaCourseDao.deleteById(id);
    }
}
