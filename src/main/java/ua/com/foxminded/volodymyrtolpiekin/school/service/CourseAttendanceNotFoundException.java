package ua.com.foxminded.volodymyrtolpiekin.school.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Course Attendance Not Found") //404
public class CourseAttendanceNotFoundException extends RuntimeException {
    public CourseAttendanceNotFoundException(int studentId){
        super(String.format("CourseAttendanceNotFoundException for student with id=%d", studentId));
    }
    public CourseAttendanceNotFoundException(String courseName){
        super(String.format("CourseAttendanceNotFoundException for course Name=%s", courseName));
    }
}