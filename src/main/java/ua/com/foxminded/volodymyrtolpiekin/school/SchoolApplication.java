package ua.com.foxminded.volodymyrtolpiekin.school;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class SchoolApplication implements CommandLineRunner {
	@Autowired
	private final Menu menu;

	public SchoolApplication(Menu menu) {
		this.menu = menu;
	}

	public static void main(String[] args) {
		log.info("STARTING THE APPLICATION");
		SpringApplication.run(SchoolApplication.class, args);
		log.info("APPLICATION FINISHED");
	}

	@Override
	public void run (String[] args) {

		log.info("EXECUTING : menu driven app");
		menu.start();
	}
}
