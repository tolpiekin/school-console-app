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
    public List<Student> getCourseStudents(String courseName){
        return courseDAOImpl.getCourseStudents(courseName);
    }

    @Override
    public boolean addStudentToCourse(Student student, Course course) {
        return courseDAOImpl.addStudentToCourse(student.getId(), course.getId());
    }

    @Override
    public boolean ifStudentAtCourse(Student student, Course course){
        return courseDAOImpl.ifStudentAtCourse(student.getId(), course.getId());
    }

    @Override
    public boolean removeStudentFromCourse(Student student, Course course){
        if (ifStudentAtCourse(student, course)) {
            return courseDAOImpl.removeStudentFromCourse(student.getId(), course.getId());
        }
        return false;
    }
}
