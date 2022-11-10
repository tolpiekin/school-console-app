package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
        try {
            return Optional.of(jCourseDao.findById(id)).orElseThrow(()->
                    new CourseNotFoundException(id));
        }
        catch(CourseNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Not Found", exc);
        }
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
        Optional<Course> returnedCourse;
        try{
            returnedCourse = Optional.of(findById(id)).orElseThrow(()->
                    new CourseNotFoundException(id));
        }
        catch (CourseNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Not Found", exc);
        }
        jCourseDao.deleteById(returnedCourse.get().getCourseId());
    }
}
