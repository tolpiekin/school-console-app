package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
        try {
            Optional<List<CourseAttendance>> studentsAtCourse = Optional.of(jCourseAttendanceDao
                    .getByCourseId(courseServiceImpl.findByName(courseName).orElseThrow(() ->
                    new CourseAttendanceNotFoundException(courseName))));
            return studentsAtCourse.get().stream().map(studentAtCourse ->
                            studentServiceImpl.findById(studentAtCourse.getStudent().getStudentId()).get())
                    .collect(Collectors.toList());
        } catch (CourseAttendanceNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Attendance not found", exc);
        }
    }

    @Override
    public List<Course> getCoursesOfStudent(Student student){
        try {
            Optional<List<CourseAttendance>> coursesOfStudent = Optional.of(Optional.of(jCourseAttendanceDao
                    .getByStudent(student)).orElseThrow(() -> new CourseAttendanceNotFoundException(student.getStudentId())));

            return coursesOfStudent.get().stream().map(courseOfStudent ->
                            courseServiceImpl.findById(courseOfStudent.getCourse().getCourseId()).get())
                    .collect(Collectors.toList());
        } catch (CourseAttendanceNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Attendance not found", exc);
        }
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
