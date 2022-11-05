package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.JpaCourseAttendanceDao;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.CourseAttendance;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseAttendanceServiceImpl implements CourseAttendanceService {
    private final JpaCourseAttendanceDao jpaCourseAttendanceDAO;
    private final CourseServiceImpl courseServiceImpl;
    private final StudentServiceImpl studentServiceImpl;

    @Autowired
    public CourseAttendanceServiceImpl(JpaCourseAttendanceDao jpaCourseAttendanceDAO, CourseServiceImpl courseServiceImpl, StudentServiceImpl studentServiceImpl) {
        this.jpaCourseAttendanceDAO = jpaCourseAttendanceDAO;
        this.courseServiceImpl = courseServiceImpl;
        this.studentServiceImpl = studentServiceImpl;
    }

    @Override
    public List<Student> findStudentsByCourseName(String courseName){
        int courseId = courseServiceImpl.findByName(courseName).get().getId();
        List<CourseAttendance> studentsAtCourse = jpaCourseAttendanceDAO.findByCourseId(courseId);
        return studentsAtCourse.stream().map(studentAtCourse ->
                        studentServiceImpl.findById(studentAtCourse.getStudent().getId()).get())
                .collect(Collectors.toList());
    }

    @Override
    public List<Course> findCoursesByStudent(int studentId){
        List<CourseAttendance> coursesOfStudent = jpaCourseAttendanceDAO.findByStudentId(studentId);
        return coursesOfStudent.stream().map(courseOfStudent ->
                        courseServiceImpl.findById(courseOfStudent.getCourse().getId()).get())
                .collect(Collectors.toList());

    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        CourseAttendance courseAttendance = new CourseAttendance();
        courseAttendance.setStudent(studentServiceImpl.findById(studentId).get());
        courseAttendance.setCourse(courseServiceImpl.findById(courseId).get());
        jpaCourseAttendanceDAO.save(courseAttendance);
    }

    @Override
    public boolean ifStudentAtCourse(int studentId, int courseId){
        return jpaCourseAttendanceDAO.findByStudentId(studentId).contains(courseId);
    }

    @Override
    public void delete(int studentId, int courseId) {
        if (ifStudentAtCourse(studentId, courseId)) {
            jpaCourseAttendanceDAO.findByStudentId(studentId).forEach(a -> {
                        if (a.getCourse().getId() == courseId) ;
                        jpaCourseAttendanceDAO.deleteById(a.getId());
                    }
            );
        } else {
            throw new EntityNotFoundException(String.format("404. Student with id %d not attending course id %d", studentId, courseId));
        }
    }
}
