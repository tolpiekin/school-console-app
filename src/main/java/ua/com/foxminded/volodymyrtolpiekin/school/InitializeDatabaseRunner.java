package ua.com.foxminded.volodymyrtolpiekin.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeDatabaseRunner implements ApplicationRunner {
    @Autowired
    DatabaseStartup databaseStartup;
    @Override
    public void run(ApplicationArguments args) {
        databaseStartup.init();
    }
}
