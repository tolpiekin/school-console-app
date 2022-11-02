package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.jpa.JpaCourseDao;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;

import javax.persistence.EntityManager;
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
    public List<Course> getAll() {
        return jpaCourseDao.getAll();
    }

    @Override
    public List<Course> findCoursesByStudentId(int studentId) {
        return jpaCourseDao.findCoursesByStudentId(studentId);
    }

    @Override
    public Optional<Course> addCourse(Course course) {
        return jpaCourseDao.addCourse(course);
    }

    @Override
    public Optional<Course> updateCourse(Course course) {
        return jpaCourseDao.updateCourse(course);
    }

    @Override
    public void deleteById(int id){
        jpaCourseDao.deleteById(id);
    }
}
