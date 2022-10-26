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
    public List<Student> getCourseStudents(String courseName){
        return courseAttendanceDAOImpl.getStudentsByCourseName(courseName);
    }

    @Override
    public boolean addStudentToCourse(Student student, Course course) {
        return courseAttendanceDAOImpl.addStudentToCourse(student.getId(), course.getId());
    }

    @Override
    public boolean ifStudentAtCourse(Student student, Course course){
        return courseAttendanceDAOImpl.isStudentAtCourse(student.getId(), course.getId());
    }

    @Override
    public boolean removeStudentFromCourse(Student student, Course course){
        if (ifStudentAtCourse(student, course)) {
            return courseAttendanceDAOImpl.removeStudentFromCourse(student.getId(), course.getId());
        }
        return false;
    }
}
