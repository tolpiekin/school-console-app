package ua.com.foxminded.volodymyrtolpiekin.school.models;

import javax.persistence.*;

@Entity
@Table(name = "course_attendance")
public class CourseAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int courseId;
    private int studentId;

    public CourseAttendance() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseAttendance that = (CourseAttendance) o;
        return courseId == that.courseId && studentId == that.studentId;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = (37 * result) + studentId + courseId;
        return result;
    }
}
