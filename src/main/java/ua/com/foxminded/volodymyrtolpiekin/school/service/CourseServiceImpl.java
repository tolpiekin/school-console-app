package ua.com.foxminded.volodymyrtolpiekin.school.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
    public Optional<Course> findById(int id) {
        try {
            return Optional.of(jpaCourseDao.findById(id)).orElseThrow(() ->
                    new CourseNotFoundException(id));
        }
        catch (CourseNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Not Found", exc);
        }
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
    public void deleteById(int id) {
        Optional<Course> returnedCourse;
        try {
            returnedCourse = Optional.of(jpaCourseDao.findById(id).orElseThrow(() ->
                    new CourseNotFoundException(id)));
        }
        catch (CourseNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Not Found", exc);
        }
        jpaCourseDao.deleteById(returnedCourse.get().getId());
    }
}
