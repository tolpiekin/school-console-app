package ua.com.foxminded.volodymyrtolpiekin.school;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class JdbcApiApplication implements CommandLineRunner {
	public static void main(String[] args) {
		log.info("STARTING THE APPLICATION");
		SpringApplication.run(JdbcApiApplication.class, args);
		log.info("APPLICATION FINISHED");
	}

	@Override
	public void run (String[] args) {
		log.info("EXECUTING : menu driven app");


	}


}
