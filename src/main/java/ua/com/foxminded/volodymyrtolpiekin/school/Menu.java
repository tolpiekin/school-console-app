package ua.com.foxminded.volodymyrtolpiekin.school;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Service
@Log4j2
public class Menu {
    private final GroupServiceImpl groupServiceImpl;
    private final CourseServiceImpl courseServiceImpl;
    private final StudentServiceImpl studentServiceImpl;
    private final CourseAttendanceServiceImpl courseAttendanceServiceImpl;

    @Autowired
    public Menu(GroupServiceImpl groupServiceImpl, CourseServiceImpl courseServiceImpl, StudentServiceImpl
            studentServiceImpl, CourseAttendanceServiceImpl courseAttendanceServiceImpl) {
        this.groupServiceImpl = groupServiceImpl;
        this.courseServiceImpl = courseServiceImpl;
        this.studentServiceImpl = studentServiceImpl;
        this.courseAttendanceServiceImpl = courseAttendanceServiceImpl;
    }

    public void start() {
        boolean inMenu = true;
        try(Scanner sc = new Scanner(System.in)) {

            while(inMenu) {

                handleMenu(MAIN_MENU, ENTER_YOUR_CHOICE);

                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == GROUP_SMALLER_THAN) {
                    groupSmallerThan(sc);
                } else if (choice == COURSE_ATTENDANCE) {
                    courseAttendance(sc);
                } else if (choice == ADD_STUDENT) {
                    addStudent(sc);
                } else if (choice == DEL_STUDENT) {
                    delStudent(sc);
                } else if (choice == ADD_STUDENT_TO_COURSE) {
                    addStudentToCourse(sc);
                } else if (choice == DEL_STUDENT_FROM_COURSE) {
                    deleteStudentFromCourse(sc);
                } else if (choice == EXIT) {
                    inMenu = false;
                }
            }
        }
    }

    private void delStudent(Scanner sc) {
        String studentsList = studentServiceImpl.getAll().stream().map(s-> String.format(STUDENT_OUTPUT_TEMPLATE,
                        s.getId(), s.getFirstName(), s.getLastName())).collect(Collectors.joining("\n"));

        handleMenu(DEL_STUDENT_TITLE + "\n" + studentsList, STUDENT_ID_REQUEST);

        int idToDelete = sc.nextInt();
        sc.nextLine();

        studentServiceImpl.delStudent(idToDelete);

        System.out.printf(DEL_STUDENT_CONFIRMATION, idToDelete);

        pressEnterToContinue(sc);
    }

    private void deleteStudentFromCourse(Scanner sc) {
        String studentsList = studentServiceImpl.getAll().stream().map(s-> String.format(STUDENT_OUTPUT_TEMPLATE,
                        s.getId(), s.getFirstName(), s.getLastName())).collect(Collectors.joining("\n"));

        handleMenu(DEL_STUD_FROM_COURSE_TITLE + "\n" + studentsList, STUDENT_ID_REQUEST);

        int studentId = sc.nextInt();
        sc.nextLine();

        if (courseAttendanceServiceImpl.findCoursesByStudent(studentId).isEmpty()) {
            System.out.println(STUDENT_NOT_ATTENDING_ANY_COURSE);
        } else {
            String currentCourses = courseAttendanceServiceImpl.findCoursesByStudent(studentId).stream().map(course ->
                    String.format(COURSE_LIST_TEMPLATE, course.getId(), course.getCourseName())).collect(Collectors
                    .joining("\n"));

            handleMenu(currentCourses, COURSE_ID_REQUEST);

            int courseId = sc.nextInt();
            sc.nextLine();
            courseAttendanceServiceImpl.delete(studentId, courseId);

            System.out.println(DEL_FROM_COURSE_CONFIRMATION);
        }

        pressEnterToContinue(sc);
    }

    private void addStudentToCourse(Scanner sc) {
        String studentsList = studentServiceImpl.getAll().stream().map(s-> String.format(STUDENT_OUTPUT_TEMPLATE,
                        s.getId(), s.getFirstName(), s.getLastName())).collect(Collectors.joining("\n"));

        handleMenu(ADD_STUD_TO_COURSE_TITLE  + "\n" + studentsList, STUDENT_ID_REQUEST);

        int studentId = sc.nextInt();
        sc.nextLine();

        List<Course> courses = courseServiceImpl.getAll();
        courses.removeAll(courseAttendanceServiceImpl.findCoursesByStudent(studentId));

        String coursesList = courses.stream().map(c-> c.getId() + ". " + c.getCourseName()).collect(Collectors
                .joining("\n"));

        handleMenu(coursesList, COURSE_ID_REQUEST);

        int courseId = sc.nextInt();
        sc.nextLine();

        Course course = courseServiceImpl.findById(courseId).get();
        courseAttendanceServiceImpl.addStudentToCourse(studentId, courseId);

        System.out.printf(ADD_STUDENT_TO_COURSE_CONFIRMATION, studentId, courseId);

        pressEnterToContinue(sc);
    }

    private void addStudent(Scanner sc) {
        String studentList = studentServiceImpl.getAll().stream().map(s-> s.getId() + ". " + s.getFirstName() +
                        s.getLastName()).collect(Collectors.joining("\n"));
        String groupsList = groupServiceImpl.getAll().stream().map(g-> g.getId() + ". " + g.getGroupName())
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

        pressEnterToContinue(sc);
    }

    private void courseAttendance(Scanner sc) {
        String coursesList = courseServiceImpl.getAll().stream().map(Course::getCourseName).collect(Collectors
                .joining("\n"));

        handleMenu(COURSE_ATTENDANCE_TITLE + "\n" + coursesList, COURSE_NAME_REQUEST);

        String courseName = sc.nextLine();

        System.out.println(courseName + " course attendees:");

        Course course = courseServiceImpl.findByName(courseName).get();
        courseAttendanceServiceImpl.findStudentsByCourseName(courseName).forEach(student ->
                System.out.format("%s %s%n", student.getFirstName(), student.getLastName()));

        pressEnterToContinue(sc);
    }

    private void groupSmallerThan(Scanner sc) {

        handleMenu(GROUP_SMALLER_THAN_TITLE, GROUP_SMALLER_THAN_REQUEST);

        int numberStudentsPerGroup = sc.nextInt();
        sc.nextLine();

        System.out.println("Group Name, Number of students");

        groupServiceImpl.findGroupLessThan(numberStudentsPerGroup).forEach(g -> System.out.println(g));
        pressEnterToContinue(sc);
    }

    private void handleMenu(String menu, String request) {
        System.out.println(menu);
        System.out.println(request);
    }

    private static void pressEnterToContinue(Scanner sc) {
        System.out.println(PRESS_ENTER_TO_CONTINUE);
        sc.nextLine();
    }
}
