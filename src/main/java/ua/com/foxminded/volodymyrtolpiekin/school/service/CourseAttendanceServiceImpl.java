package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.JpaCourseAttendanceDao;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.CourseAttendance;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseAttendanceServiceImpl implements CourseAttendanceService {
    private final JpaCourseAttendanceDao jpaCourseAttendanceDao;
    private final CourseServiceImpl courseServiceImpl;
    private final StudentServiceImpl studentServiceImpl;

    @Autowired
    public CourseAttendanceServiceImpl(JpaCourseAttendanceDao jpaCourseAttendanceDao, CourseServiceImpl courseServiceImpl, StudentServiceImpl studentServiceImpl) {
        this.jpaCourseAttendanceDao = jpaCourseAttendanceDao;
        this.courseServiceImpl = courseServiceImpl;
        this.studentServiceImpl = studentServiceImpl;
    }

    @Override
    public List<Student> findStudentsByCourseName(String courseName){
        int courseId = courseServiceImpl.findByName(courseName).get().getId();
        try {
            Optional<List<CourseAttendance>> studentsAtCourse = Optional.of(jpaCourseAttendanceDao
                    .findByCourseId(courseId).orElseThrow(() -> new CourseAttendanceNotFoundException(courseName)));
            return studentsAtCourse.get().stream().map(studentAtCourse ->
                            studentServiceImpl.findById(studentAtCourse.getStudent().getId()).get())
                    .collect(Collectors.toList());
        } catch (CourseAttendanceNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Attendance not found", exc);
        }

    }

    @Override
    public List<Course> findCoursesByStudent(int studentId){
        try {
            Optional<List<CourseAttendance>> coursesOfStudent = Optional.of(jpaCourseAttendanceDao
                    .findByStudentId(studentId).orElseThrow(() -> new CourseAttendanceNotFoundException(studentId)));

            return coursesOfStudent.get().stream().map(courseOfStudent ->
                            courseServiceImpl.findById(courseOfStudent.getCourse().getId()).get())
                    .collect(Collectors.toList());
        } catch (CourseAttendanceNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course Attendance not found", exc);
        }
    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        CourseAttendance courseAttendance = new CourseAttendance();
        courseAttendance.setStudent(studentServiceImpl.findById(studentId).get());
        courseAttendance.setCourse(courseServiceImpl.findById(courseId).get());
        jpaCourseAttendanceDao.save(courseAttendance);
    }

    @Override
    public boolean ifStudentAtCourse(int studentId, int courseId){
        return (jpaCourseAttendanceDao.findByCourseId(courseId).isPresent() &&
                jpaCourseAttendanceDao.findByStudentId(studentId).isPresent());
    }

    @Override
    public void delete(int studentId, int courseId) {
        if (ifStudentAtCourse(studentId, courseId)) {
            jpaCourseAttendanceDao.findByStudentId(studentId).get().forEach(ca -> {
                        if (ca.getCourse().getId() == courseId){
                        jpaCourseAttendanceDao.deleteById(ca.getId());
                    }});
        }
    }
}
