package ua.com.foxminded.volodymyrtolpiekin.school.service;

import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.JCourseAttendanceDao;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.CourseAttendance;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseAttendanceServiceImpl implements CourseAttendanceService {
    private final JCourseAttendanceDao jCourseAttendanceDao;
    private final CourseServiceImpl courseServiceImpl;
    private final StudentServiceImpl studentServiceImpl;


    @Autowired
    public CourseAttendanceServiceImpl(JCourseAttendanceDao jCourseAttendanceDao, CourseServiceImpl courseServiceImpl, StudentServiceImpl studentServiceImpl) {
        this.jCourseAttendanceDao = jCourseAttendanceDao;
        this.courseServiceImpl = courseServiceImpl;
        this.studentServiceImpl = studentServiceImpl;
    }

    @Override
    public List<Student> getStudentsAtCourse(String courseName){
        int courseId = courseServiceImpl.findByName(courseName).get().getCourseId();
        List<CourseAttendance> studentsAtCourse = jCourseAttendanceDao.getStudentsByCourseId(courseId);
        return studentsAtCourse.stream().map(studentAtCourse ->
                        studentServiceImpl.findById(studentAtCourse.getStudent().getStudentId()).get())
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> getCoursesOfStudent(int studentId){
        List<CourseAttendance> coursesOfStudent = jCourseAttendanceDao.getCoursesByStudentId(studentId);
        return coursesOfStudent.stream().map(courseOfStudent ->
                        courseServiceImpl.findById(courseOfStudent.getCourse().getCourseId()).get())
                .collect(Collectors.toList());
    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        CourseAttendance courseAttendance = new CourseAttendance();
        courseAttendance.setStudent(studentServiceImpl.findById(studentId).get());
        courseAttendance.setCourse(courseServiceImpl.findById(courseId).get());
        jCourseAttendanceDao.addStudentToCourse(courseAttendance);
    }

    @Override
    public boolean ifStudentAtCourse(int studentId, int courseId){
        return (!jCourseAttendanceDao.getCoursesByStudentId(studentId).isEmpty() &&
                !jCourseAttendanceDao.getStudentsByCourseId(courseId).isEmpty());
    }

    @Override
    public void removeStudentFromCourse(int studentId, int courseId){
        if (ifStudentAtCourse(studentId, courseId)) {
            jCourseAttendanceDao.getCoursesByStudentId(studentId).forEach(ca -> {
                if (ca.getCourse().getCourseId() == courseId){
                    jCourseAttendanceDao.deleteById(ca.getCourseAttendanceId());
                }
            });
        }
    }
}
