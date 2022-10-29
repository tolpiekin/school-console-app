package ua.com.foxminded.volodymyrtolpiekin.school;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static ua.com.foxminded.volodymyrtolpiekin.school.MenuConstants.*;

@SpringBootApplication
@Log4j2
public class JdbcApiApplication implements CommandLineRunner {
	@Autowired
	private final Menu menu;

	public JdbcApiApplication(Menu menu) {
		this.menu = menu;
	}

	public static void main(String[] args) {
		log.info("STARTING THE APPLICATION");
		SpringApplication.run(JdbcApiApplication.class, args);
		log.info("APPLICATION FINISHED");
	}

	@Override
	public void run (String[] args) {

		log.info("EXECUTING : menu driven app");
		boolean inMenu = true;
		try(Scanner sc = new Scanner(System.in)) {

			while(inMenu) {

				menu.handleMenu(MAIN_MENU, ENTER_YOUR_CHOICE);

				int choice = sc.nextInt();
				sc.nextLine();

				if (choice == GROUP_SMALLER_THAN) {
					menu.groupSmallerThan(sc);
				} else if (choice == COURSE_ATTENDANCE) {
					menu.courseAttendance(sc);
				} else if (choice == ADD_STUDENT) {
					menu.addStudent(sc);
				} else if (choice == DEL_STUDENT) {
					menu.delStudent(sc);
				} else if (choice == ADD_STUDENT_TO_COURSE) {
					menu.addStudentToCourse(sc);
				} else if (choice == DEL_STUDENT_FROM_COURSE) {
					menu.deleteStudentFromCourse(sc);
				} else if (choice == EXIT) {
					inMenu = false;
				}
			}
		}
	}
}
