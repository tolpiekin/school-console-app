package ua.com.foxminded.volodymyrtolpiekin.school.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.JpaCourseDao;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CourseServiceImpl implements CourseService{

    private final JpaCourseDao jpaCourseDao;

    public CourseServiceImpl (JpaCourseDao jpaCourseDao) {
        this.jpaCourseDao = jpaCourseDao;
    }

    @Override
    public Optional<Course> findById(int id) throws CourseNotFoundException {
        return Optional.ofNullable(jpaCourseDao.findById(id)).orElseThrow(() ->
                new CourseNotFoundException(id));
    }

    @Override
    public Optional<Course> findByName(String courseName) {
        return jpaCourseDao.findByCourseName(courseName);
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
    public void deleteById(int id) throws CourseNotFoundException {
        Optional<Course> returnedCourse = Optional.ofNullable(jpaCourseDao.findById(id).orElseThrow(() ->
                new CourseNotFoundException(id)));
        jpaCourseDao.deleteById(returnedCourse.get().getId());
    }
}
