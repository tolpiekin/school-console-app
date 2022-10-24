package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static ua.com.foxminded.volodymyrtolpiekin.school.Constants.*;

@Service
public class DatabaseStartup {
    @Autowired
    GroupServiceImpl groupServiceImpl;
    @Autowired
    CourseServiceImpl courseServiceImpl;
    @Autowired
    StudentServiceImpl studentServiceImpl;
    @Autowired
    CourseAttendanceServiceImpl courseAttendanceServiceImpl;
    @Autowired
    JdbcTemplate jdbcTemplate;
    Random random = new Random();
    private String generateGroupName() {
        char a = 'a';
        String separator = "-";
        return "" + (char) (random.nextInt(26) + a) + (char) (random.nextInt(26) + a) + separator
                + random.nextInt(10) + random.nextInt(10);
    }

    public void generateGroups() {
        if (groupServiceImpl.getAll().size() == 0) {
            IntStream.range(0, NUMBER_OF_GROUPS).forEach(i ->
                    groupServiceImpl.addGroup(new Group(i + 1, generateGroupName())));
        }
    }

    public void generateCourses() {
        if (courseServiceImpl.getAll().size() == 0) {
            IntStream.range(0, COURSES.length).forEach(i ->
                    courseServiceImpl.addCourse(new Course(i + 1, COURSES[i],
                            String.format(COURSE_DESCRIPTION, COURSES[i]))));
        }

    }

    public void generateStudents() {
        if (studentServiceImpl.getAll().size() == 0) {
            List<Group> groups = groupServiceImpl.getAll();
            IntStream.range(0, NUMBER_OF_STUDENTS).forEach(i ->
                    studentServiceImpl.addStudent(new Student(i + 1, groups.get(random.nextInt(NUMBER_OF_GROUPS)).getId(),
                            FIRST_NAMES[random.nextInt(FIRST_NAMES.length)],
                            LAST_NAMES[random.nextInt(LAST_NAMES.length)])));
        }

    }

    public void assignStudentsToCourses() {
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
