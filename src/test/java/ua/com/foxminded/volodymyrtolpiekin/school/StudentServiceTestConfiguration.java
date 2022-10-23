package ua.com.foxminded.volodymyrtolpiekin.school;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import ua.com.foxminded.volodymyrtolpiekin.school.spring.service.StudentService;

@Profile("test")
@Configuration
public class StudentServiceTestConfiguration {
    @Bean
    @Primary
    public StudentService studentService() {
        return Mockito.mock(StudentService.class);
    }
}
