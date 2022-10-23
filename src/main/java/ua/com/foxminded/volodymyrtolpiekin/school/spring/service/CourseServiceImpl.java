package ua.com.foxminded.volodymyrtolpiekin.school.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;
import ua.com.foxminded.volodymyrtolpiekin.school.spring.dao.CourseDAOImpl;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseDAOImpl jdbcCourseDAOImpl;

    @Override
    public Optional<Course> findById(int id) {
        return jdbcCourseDAOImpl.findById(id);
    }

    @Override
    public Optional<Course> findByName(String name) {
        return jdbcCourseDAOImpl.findByName(name);
    }

    @Override
    public List<Course> getAll() {
        return jdbcCourseDAOImpl.getAll();
    }

    @Override
    public Optional<Course> addCourse(Course course) {
        return jdbcCourseDAOImpl.addItem(course);
    }

    @Override
    public Optional<Course> updateCourse(Course course) {
        return jdbcCourseDAOImpl.updateItem(course);
    }

    @Override
    public void deleteById(int id){
        jdbcCourseDAOImpl.deleteById(id);
    }

    @Override
    public List<Student> getCourseStudents(String courseName){
        return jdbcCourseDAOImpl.getCourseStudents(courseName);
    }

    @Override
    public boolean addStudentToCourse(Student student, Course course) {
        return jdbcCourseDAOImpl.addStudentToCourse(student.getId(), course.getId());
    }

    @Override
    public boolean ifStudentAtCourse(Student student, Course course){
        return jdbcCourseDAOImpl.ifStudentAtCourse(student.getId(), course.getId());
    }

    @Override
    public boolean removeStudentFromCourse(Student student, Course course){
        if (ifStudentAtCourse(student, course)) {
            return jdbcCourseDAOImpl.removeStudentFromCourse(student.getId(), course.getId());
        }
        return false;
    }
}
