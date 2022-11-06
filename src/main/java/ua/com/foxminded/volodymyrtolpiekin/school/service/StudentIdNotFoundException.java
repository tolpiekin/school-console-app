package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Group Not Found") //404
public class StudentIdNotFoundException extends RuntimeException {

    public StudentIdNotFoundException(int id) {
        super(String.format("StudentNotFoundException with id=%d", id));
    }
}
