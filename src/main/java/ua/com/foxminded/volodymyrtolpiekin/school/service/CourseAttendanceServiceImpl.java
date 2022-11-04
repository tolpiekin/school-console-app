package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.jpa.JpaCourseAttendanceDAO;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;

@Service
public class CourseAttendanceServiceImpl implements CourseAttendanceService {
    private final JpaCourseAttendanceDAO jpaCourseAttendanceDAO;
    private final CourseServiceImpl courseServiceImpl;

    @Autowired
    public CourseAttendanceServiceImpl(JpaCourseAttendanceDAO jpaCourseAttendanceDAO, CourseServiceImpl courseServiceImpl) {
        this.jpaCourseAttendanceDAO = jpaCourseAttendanceDAO;
        this.courseServiceImpl = courseServiceImpl;
    }

    @Override
    public List<Student> findStudentsByCourseName(String courseName){
        Course course = courseServiceImpl.findByName(courseName).get();
        return jpaCourseAttendanceDAO.findStudentsByCourse(course.getId());
    }

    @Override
    public List<Course> findCoursesByStudent(int studentId){
        return jpaCourseAttendanceDAO.findCoursesByStudent(studentId);
    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        jpaCourseAttendanceDAO.addStudentToCourse(studentId, courseId);
    }

    @Override
    public boolean ifStudentAtCourse(int studentId, int courseId){
        Course course = courseServiceImpl.findById(courseId).get();
        List<Course> courses = jpaCourseAttendanceDAO.findCoursesByStudent(studentId);
        if (courses.contains(course)) {
            return true;
        }
        return false;
    }

    @Override
    public void removeStudentFromCourse(int studentId, int courseId){
        if (ifStudentAtCourse(studentId, courseId)) {
            jpaCourseAttendanceDAO.removeStudentFromCourse(studentId, courseId);
        }
    }
}
