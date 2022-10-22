package ua.com.foxminded.volodymyrtolpiekin.school.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;
import ua.com.foxminded.volodymyrtolpiekin.school.spring.service.CourseService;
import ua.com.foxminded.volodymyrtolpiekin.school.spring.service.GroupService;
import ua.com.foxminded.volodymyrtolpiekin.school.spring.service.StudentService;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static ua.com.foxminded.volodymyrtolpiekin.school.Constants.*;

@Service
public class PopulateDatabase {
    GroupService groupService;
    CourseService courseService;
    StudentService studentService;
    JdbcTemplate jdbcTemplate;
    Random random = new Random();

    private String generateGroupName() {
        char a = 'a';
        String separator = "-";
        return "" + (char) (random.nextInt(26) + a) + (char) (random.nextInt(26) + a) + separator
                + random.nextInt(10) + random.nextInt(10);
    }

    private void generateGroups() {
        IntStream.range(0, NUMBER_OF_GROUPS).forEach(i ->
                groupService.addGroup(new Group(i + 1, generateGroupName())));
    }

    private void generateCourses() {
        IntStream.range(0, COURSES.length).forEach(i ->
                courseService.addCourse(new Course(i + 1, COURSES[i],
                        String.format(COURSE_DESCRIPTION, COURSES[i]))));
    }

    private void generateStudents() {
        List<Group> groups = groupService.getAll();
        IntStream.range(0, NUMBER_OF_STUDENTS).forEach(i ->
                studentService.addStudent(new Student(i + 1, groups.get(random.nextInt(NUMBER_OF_GROUPS)).getId(),
                        FIRST_NAMES[random.nextInt(FIRST_NAMES.length)],
                        LAST_NAMES[random.nextInt(LAST_NAMES.length)])));
    }

    private void assignStudentsToCourses() {
        jdbcTemplate.update(QUERY_COURSE_ATTENDANCE_CREATE);
        List<Student> students = studentService.getAll();
        List<Course> courses = courseService.getAll();
        students.forEach(student -> IntStream.range(0, random.nextInt(COURSES_LIMIT + 1)).forEach(i -> {
            Course course = courses.get(random.nextInt(courses.size()));
            if (!courseService.ifStudentAtCourse(student, course)) {
                courseService.addStudentToCourse(student, course);
            }
        }));
    }
}
