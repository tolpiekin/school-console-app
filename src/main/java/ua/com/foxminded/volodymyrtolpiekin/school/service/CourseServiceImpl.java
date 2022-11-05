package ua.com.foxminded.volodymyrtolpiekin.school.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.JpaCourseDao;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;

import javax.persistence.EntityNotFoundException;
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
        Optional<Course> returnedCourse = jpaCourseDao.findById(id);
        if(returnedCourse.isPresent()) {
            return returnedCourse;
        } else {
            throw new EntityNotFoundException(String.format("404.Course with id %d not found", id));
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
    public void deleteById(int id){
        Optional<Course> returnedCourse = jpaCourseDao.findById(id);
        if(returnedCourse.isPresent()) {
            jpaCourseDao.deleteById(id);
        } else {
            throw new EntityNotFoundException(String.format("404. Course with id %d not found", id));
        }
    }
}
