package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Course Not Found") //404
public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(int id){
        super(String.format("CourseNotFoundException with id=%d", id));
    }
}