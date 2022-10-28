package ua.com.foxminded.volodymyrtolpiekin.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;
import ua.com.foxminded.volodymyrtolpiekin.school.service.CourseAttendanceServiceImpl;
import ua.com.foxminded.volodymyrtolpiekin.school.service.CourseServiceImpl;
import ua.com.foxminded.volodymyrtolpiekin.school.service.GroupServiceImpl;
import ua.com.foxminded.volodymyrtolpiekin.school.service.StudentServiceImpl;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static ua.com.foxminded.volodymyrtolpiekin.school.MenuConstants.*;

@SpringBootApplication
public class Menu implements ApplicationRunner {
    private final GroupServiceImpl groupServiceImpl;
    private final CourseServiceImpl courseServiceImpl;
    private final StudentServiceImpl studentServiceImpl;
    private final CourseAttendanceServiceImpl courseAttendanceServiceImpl;

    @Autowired
    public Menu(GroupServiceImpl groupServiceImpl, CourseServiceImpl courseServiceImpl, StudentServiceImpl studentServiceImpl, CourseAttendanceServiceImpl courseAttendanceServiceImpl) {
        this.groupServiceImpl = groupServiceImpl;
        this.courseServiceImpl = courseServiceImpl;
        this.studentServiceImpl = studentServiceImpl;
        this.courseAttendanceServiceImpl = courseAttendanceServiceImpl;
    }

    @Override
    public void run(ApplicationArguments args) {
        boolean inMenu = true;
        try(Scanner sc = new Scanner(System.in)) {

        while(inMenu) {

            handleMenu(MAIN_MENU, ENTER_YOUR_CHOICE);

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == GROUP_SMALLER_THAN) {
                findGroupWithStudentsLessThenNumber(sc);
            } else if (choice == COURSE_ATTENDANCE) {
                showStudentsAttendingCourse(sc);
            } else if (choice == ADD_STUDENT) {
                addNewStudent(sc);
            } else if (choice == DEL_STUDENT) {
                deleteStudent(sc);
            } else if (choice == ADD_STUD_TO_COURSE) {
                addStudentToCourse(sc);
            } else if (choice == DEL_STUD_FROM_COURSE) {
                deleteStudentFromCourse(sc);
            } else if (choice == EXIT) {
                inMenu = false;
            }
        }
        }
    }

    private void deleteStudent(Scanner sc) {
        String studentsList = studentServiceImpl.getAll().stream().map(s->
                        String.format(STUDENT_OUTPUT_TEMPLATE, s.getId(), s.getFirstName(), s.getLastName()))
                .collect(Collectors.joining("\n"));
        handleMenu(DEL_STUDENT_TITLE + "\n" + studentsList, STUDENT_ID_REQUEST);
        int idToDelete = sc.nextInt();
        sc.nextLine();
        studentServiceImpl.delStudent(idToDelete);
        System.out.printf(DEL_STUDENT_CONFIRMATION, idToDelete);
        System.out.println(PRESS_ENTER_TO_CONTINUE);
        sc.nextLine();
    }

    private void deleteStudentFromCourse(Scanner sc) {
        String studentsList = studentServiceImpl.getAll().stream().map(s->
                        String.format(STUDENT_OUTPUT_TEMPLATE, s.getId(), s.getFirstName(), s.getLastName()))
                .collect(Collectors.joining("\n"));
        handleMenu(DEL_STUD_FROM_COURSE_TITLE + "\n" + studentsList, STUDENT_ID_REQUEST);
        int studentId = sc.nextInt();
        sc.nextLine();
        if (courseAttendanceServiceImpl.getCoursesOfStudent(studentId).isEmpty()) {
            System.out.println(STUDENT_NOT_ATTENDING_ANY_COURSE);
        } else {
            String currentCourses = courseAttendanceServiceImpl.getCoursesOfStudent(studentId).stream()
                    .map(course -> String.format(COURSE_LIST_TEMPLATE, course.getId(), course.getName())).collect(Collectors.joining("\n"));
            handleMenu(currentCourses, COURSE_ID_REQUEST);
            int courseId = sc.nextInt();
            sc.nextLine();
            courseAttendanceServiceImpl.removeStudentFromCourse(studentId, courseId);
            System.out.println(DEL_FROM_COURSE_CONFIRMATION);
        }
        System.out.println(PRESS_ENTER_TO_CONTINUE);
        sc.nextLine();
    }

    private void addStudentToCourse(Scanner sc) {
        String studentsList = studentServiceImpl.getAll().stream().map(s->
                        String.format(STUDENT_OUTPUT_TEMPLATE, s.getId(), s.getFirstName(), s.getLastName()))
                .collect(Collectors.joining("\n"));
        handleMenu(ADD_STUD_TO_COURSE_TITLE  + "\n" + studentsList, STUDENT_ID_REQUEST);
        int studentId = sc.nextInt();
        sc.nextLine();
        List<Course> courses = courseServiceImpl.getAll();
        List<Course> attendingCourses = courseAttendanceServiceImpl.getCoursesOfStudent(studentId);
        courses.removeAll(attendingCourses);
        String coursesList = courses.stream().map(c-> c.getId() + ". " + c.getName()).collect(Collectors.joining("\n"));
        handleMenu(coursesList, COURSE_ID_REQUEST);
        int courseId = sc.nextInt();
        sc.nextLine();
        courseAttendanceServiceImpl.addStudentToCourse(studentId, courseId);
        System.out.printf(ADD_STUDENT_TO_COURSE_CONFIRMATION, studentId, courseId);
        System.out.println(PRESS_ENTER_TO_CONTINUE);
        sc.nextLine();
    }

    private void addNewStudent(Scanner sc) {
        String studentList = studentServiceImpl.getAll().stream().map(s-> s.getId() + ". " + s.getFirstName() + s.getLastName())
                .collect(Collectors.joining("\n"));
        String groupsList = groupServiceImpl.getAll().stream().map(g-> g.getId() + ". " + g.getName())
                .collect(Collectors.joining("\n"));
        handleMenu(ADD_STUDENT_TITLE + "\n" + studentList, STUDENT_ID_REQUEST);
        int studentId = sc.nextInt();
        sc.nextLine();
        handleMenu(ADD_STUDENT_TITLE + "\n" + groupsList, GROUP_ID_REQUEST);
        int groupId = sc.nextInt();
        sc.nextLine();
        handleMenu(ADD_STUDENT_TITLE, FIRST_NAME_REQUEST);
        String firstName = sc.nextLine();
        handleMenu(ADD_STUDENT_TITLE, LAST_NAME_REQUEST);
        String lastName = sc.nextLine();
        studentServiceImpl.addStudent(new Student(studentId, groupId, firstName, lastName));
        System.out.printf(ADD_STUDENT_CONFIRMATION, firstName, lastName, studentId);
        System.out.println(PRESS_ENTER_TO_CONTINUE);
        sc.nextLine();
    }

    private void showStudentsAttendingCourse(Scanner sc) {
        String coursesList = courseServiceImpl.getAll().stream().map(Course::getName).collect(Collectors
                .joining("\n"));
        handleMenu(COURSE_ATTENDANCE_TITLE + "\n" + coursesList, COURSE_NAME_REQUEST);
        String courseName = sc.nextLine();
        System.out.println(courseName + " course attendees:");
        courseAttendanceServiceImpl.getStudentsAtCourse(courseName).forEach(m ->
                System.out.format("%s %s%n", m.getFirstName(), m.getLastName()));
        System.out.println(PRESS_ENTER_TO_CONTINUE);
        sc.nextLine();
    }

    private void findGroupWithStudentsLessThenNumber(Scanner sc) {
        handleMenu(GROUP_SMALLER_THAN_TITLE, GROUP_SMALLER_THAN_REQUEST);
        int numberStudentsPerGroup = sc.nextInt();
        sc.nextLine();
        System.out.println("Group Name | Number of Students");
        groupServiceImpl.smallerThen(numberStudentsPerGroup).forEach(m ->
                System.out.format("%10s%10s%n", m.get("group_name"), m.get("count")));
        System.out.println(PRESS_ENTER_TO_CONTINUE);
        sc.nextLine();
    }

    private void handleMenu(String menu, String request) {
        System.out.println(menu);
        System.out.println(request);
    }
}
