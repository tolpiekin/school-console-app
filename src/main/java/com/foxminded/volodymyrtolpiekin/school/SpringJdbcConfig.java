package com.foxminded.volodymyrtolpiekin.school;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.foxminded.volodymyrtolpiekin.school")
@PropertySource("classpath:database.properties")
public class SpringJdbcConfig {
    @Value("${DB_URL}")
    private String dbUrl;
    @Value("${DB_USER}")
    private String dbUser;
    @Value("${DB_PASSWORD}")
    private String dbPassword;
    @Value("${DB_DRIVER}")
    private String dbDriver;
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(dbUrl);
        driverManagerDataSource.setUsername(dbUser);
        driverManagerDataSource.setPassword(dbPassword);
        driverManagerDataSource.setDriverClassName(dbDriver);

        return driverManagerDataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate (DataSource dataSource) {
        return this.jdbcTemplate(dataSource);
    }
}
