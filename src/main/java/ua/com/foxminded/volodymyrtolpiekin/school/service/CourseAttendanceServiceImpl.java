package ua.com.foxminded.volodymyrtolpiekin.school.service;

import ua.com.foxminded.volodymyrtolpiekin.school.dao.CourseAttendanceDAOImpl;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;

public class CourseAttendanceServiceImpl implements CourseAttendanceService {
    private CourseAttendanceDAOImpl courseAttendanceDAOImpl;

    @Override
    public List<Student> getCourseStudents(String courseName){
        return courseAttendanceDAOImpl.getCourseStudents(courseName);
    }

    @Override
    public boolean addStudentToCourse(Student student, Course course) {
        return courseAttendanceDAOImpl.addStudentToCourse(student.getId(), course.getId());
    }

    @Override
    public boolean ifStudentAtCourse(Student student, Course course){
        return courseAttendanceDAOImpl.ifStudentAtCourse(student.getId(), course.getId());
    }

    @Override
    public boolean removeStudentFromCourse(Student student, Course course){
        if (ifStudentAtCourse(student, course)) {
            return courseAttendanceDAOImpl.removeStudentFromCourse(student.getId(), course.getId());
        }
        return false;
    }
}
