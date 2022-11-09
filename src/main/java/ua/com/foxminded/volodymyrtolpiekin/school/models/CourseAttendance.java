package ua.com.foxminded.volodymyrtolpiekin.school.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CourseAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int courseAttendanceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    Course course;

    public CourseAttendance() {
    }

    public int getCourseAttendanceId() {
        return courseAttendanceId;
    }

    public void setCourseAttendanceId(int courseAttendanceId) {
        this.courseAttendanceId = courseAttendanceId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseAttendance that = (CourseAttendance) o;
        return courseAttendanceId == that.courseAttendanceId && Objects.equals(student, that.student) && Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = (37 * result) + courseAttendanceId;
        return result;
    }
}