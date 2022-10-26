package ua.com.foxminded.volodymyrtolpiekin.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;
import ua.com.foxminded.volodymyrtolpiekin.school.service.CourseAttendanceServiceImpl;
import ua.com.foxminded.volodymyrtolpiekin.school.service.CourseServiceImpl;
import ua.com.foxminded.volodymyrtolpiekin.school.service.GroupServiceImpl;
import ua.com.foxminded.volodymyrtolpiekin.school.service.StudentServiceImpl;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static ua.com.foxminded.volodymyrtolpiekin.school.Constants.*;

@Service
public class DatabaseStartup {
    private final GroupServiceImpl groupServiceImpl;
    private final CourseServiceImpl courseServiceImpl;
    private final StudentServiceImpl studentServiceImpl;
    private final CourseAttendanceServiceImpl courseAttendanceServiceImpl;

    Random random = new Random();

    @Autowired
    DatabaseStartup(GroupServiceImpl groupServiceImpl, CourseServiceImpl courseServiceImpl, StudentServiceImpl
            studentServiceImpl, CourseAttendanceServiceImpl courseAttendanceServiceImpl) {
        this.groupServiceImpl = groupServiceImpl;
        this.courseServiceImpl = courseServiceImpl;
        this.studentServiceImpl = studentServiceImpl;
        this.courseAttendanceServiceImpl = courseAttendanceServiceImpl;
    }

    @PostConstruct
    public void init(){
        generateGroups();
        generateCourses();
        generateStudents();
        assignStudentsToCourses();
    }

    private String generateGroupName() {
        return "" + (char) (random.nextInt(26) + 'a') + (char) (random.nextInt(26) + 'a') + "-"
                + random.nextInt(10) + random.nextInt(10);
    }

    private void generateGroups() {
        IntStream.range(0, NUMBER_OF_GROUPS).forEach(i -> {
            Group group = new Group(i + 1, generateGroupName());
            if (!groupServiceImpl.findById(group.getId()).isPresent()) {
                groupServiceImpl.addGroup(group);
            }
                });

    }

    private void generateCourses() {
        IntStream.range(0, COURSES.length).forEach(i ->{
            Course course = new Course(i + 1, COURSES[i], String.format(COURSE_DESCRIPTION, COURSES[i]));
            if (!(courseServiceImpl.findById(course.getId())).isPresent()) {
                courseServiceImpl.addCourse(course);
            }
        });
    }

    private void generateStudents() {
        List<Group> groups = groupServiceImpl.getAll();
        IntStream.range(0, NUMBER_OF_STUDENTS).forEach(i -> {
            Student student = new Student(i + 1, groups.get(random.nextInt(NUMBER_OF_GROUPS)).getId(),
                    FIRST_NAMES[random.nextInt(FIRST_NAMES.length)],
                    LAST_NAMES[random.nextInt(LAST_NAMES.length)]);
            if (!(studentServiceImpl.findById(student.getId())).isPresent()) {
                studentServiceImpl.addStudent(student);}
        });
    }

    private void assignStudentsToCourses() {
        List<Student> students = studentServiceImpl.getAll();
        List<Course> courses = courseServiceImpl.getAll();
        students.forEach(student -> IntStream.range(0, random.nextInt(COURSES_LIMIT + 1)).forEach(i -> {
            Course course = courses.get(random.nextInt(courses.size()));
            if (!courseAttendanceServiceImpl.ifStudentAtCourse(student, course)) {
                courseAttendanceServiceImpl.addStudentToCourse(student, course);
            }
        }));
    }
}
