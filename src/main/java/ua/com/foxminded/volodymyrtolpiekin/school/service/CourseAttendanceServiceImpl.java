package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.JCourseAttendanceDao;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.CourseAttendance;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;
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
        List<CourseAttendance> studentsAtCourse = jCourseAttendanceDao.getByCourseId(courseServiceImpl
                .findById(courseId).get());
        return studentsAtCourse.stream().map(studentAtCourse ->
                        studentServiceImpl.findById(studentAtCourse.getStudent().getStudentId()).get())
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> getCoursesOfStudent(Student student){
        List<CourseAttendance> coursesOfStudent = jCourseAttendanceDao.getByStudent(student);
        return coursesOfStudent.stream().map(ca ->
                        courseServiceImpl.findById(ca.getCourse().getCourseId()).get())
                .collect(Collectors.toList());
    }

    @Override
    public void addStudentToCourse(Student student, Course course) {
        CourseAttendance courseAttendance = new CourseAttendance();
        courseAttendance.setStudent(student);
        courseAttendance.setCourse(course);
        jCourseAttendanceDao.addStudentToCourse(courseAttendance);
    }

    @Override
    public boolean ifStudentAtCourse(Student student, Course course){
        return (!jCourseAttendanceDao.getByStudent(student).isEmpty() &&
                !jCourseAttendanceDao.getByCourseId(course).isEmpty());
    }

    @Override
    public void removeStudentFromCourse(Student student, Course course){
        if (ifStudentAtCourse(student, course)) {
            jCourseAttendanceDao.getByStudent(student).forEach(ca -> {
                if (ca.getCourse().getCourseId() == course.getCourseId()){
                    jCourseAttendanceDao.deleteById(ca.getCourseAttendanceId());
                }
            });
        }
    }
}
