package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Group Not Found") //404
public class GroupNotFoundException extends RuntimeException {

    public GroupNotFoundException(int id) {
        super(String.format("GroupNotFoundException with id=%d", id));
    }
}
