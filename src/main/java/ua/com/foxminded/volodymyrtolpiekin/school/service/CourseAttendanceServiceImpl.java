package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.JpaCourseAttendanceDAO;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.CourseAttendance;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseAttendanceServiceImpl implements CourseAttendanceService {
    private final JpaCourseAttendanceDAO jpaCourseAttendanceDAO;
    private final CourseServiceImpl courseServiceImpl;
    private final StudentServiceImpl studentServiceImpl;

    @Autowired
    public CourseAttendanceServiceImpl(JpaCourseAttendanceDAO jpaCourseAttendanceDAO, CourseServiceImpl courseServiceImpl, StudentServiceImpl studentServiceImpl) {
        this.jpaCourseAttendanceDAO = jpaCourseAttendanceDAO;
        this.courseServiceImpl = courseServiceImpl;
        this.studentServiceImpl = studentServiceImpl;
    }

    @Override
    public List<Student> findStudentsByCourseName(String courseName){
        int courseId = courseServiceImpl.findByName(courseName).get().getId();
        List<CourseAttendance> studentsAtCourse = jpaCourseAttendanceDAO.findStudentsByCourseId(courseId);
        return studentsAtCourse.stream().map(student -> studentServiceImpl.findById(student.getStudentId()).get())
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> findCoursesByStudent(int studentId){
        List<CourseAttendance> coursesOfStudent = jpaCourseAttendanceDAO.findCoursesByStudentId(studentId);
        return coursesOfStudent.stream().map(course -> courseServiceImpl.findById(course.getCourseId()).get())
                .collect(Collectors.toList());

    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        CourseAttendance courseAttendance = new CourseAttendance();
        courseAttendance.setStudentId(studentId);
        courseAttendance.setCourseId(courseId);
        jpaCourseAttendanceDAO.save(courseAttendance);
    }

    @Override
    public boolean ifStudentAtCourse(int studentId, int courseId){
        return jpaCourseAttendanceDAO.findCoursesByStudentId(studentId).contains(courseId);
    }

    @Override
    public void delete(int studentId, int courseId){
        if (ifStudentAtCourse(studentId, courseId)) {
            CourseAttendance courseAttendance = new CourseAttendance();
            courseAttendance.setStudentId(studentId);
            courseAttendance.setCourseId(courseId);
            jpaCourseAttendanceDAO.delete(courseAttendance);
        }
    }
}
