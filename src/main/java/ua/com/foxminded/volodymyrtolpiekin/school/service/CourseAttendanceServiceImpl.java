package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.CourseAttendanceDAOImpl;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;

@Service
public class CourseAttendanceServiceImpl implements CourseAttendanceService {
    private final CourseAttendanceDAOImpl courseAttendanceDAOImpl;

    @Autowired
    public CourseAttendanceServiceImpl(CourseAttendanceDAOImpl courseAttendanceDAOImpl) {
        this.courseAttendanceDAOImpl = courseAttendanceDAOImpl;
    }

    @Override
    public List<Student> getStudentsAtCourse(String courseName){
        return courseAttendanceDAOImpl.getStudentsByCourseName(courseName);
    }

    @Override
    public List<Course> getCoursesOfStudent(int studentId){
        return courseAttendanceDAOImpl.getCoursesByStudentId(studentId);
    }

    @Override
    public boolean addStudentToCourse(int studentId, int courseId) {
        return courseAttendanceDAOImpl.addStudentToCourse(studentId, courseId);
    }

    @Override
    public boolean ifStudentAtCourse(int studentId, int courseId){
        return courseAttendanceDAOImpl.isStudentAtCourse(studentId, courseId);
    }

    @Override
    public boolean removeStudentFromCourse(int studentId, int courseId){
        if (ifStudentAtCourse(studentId, courseId)) {
            return courseAttendanceDAOImpl.removeStudentFromCourse(studentId, courseId);
        }
        return false;
    }
}
