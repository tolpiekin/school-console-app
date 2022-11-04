package ua.com.foxminded.volodymyrtolpiekin.school.models;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String courseName;
    private String courseDescription;

    public Course() {
    }

    public Course(int id, String name, String courseDescription) {
        this.id = id;
        this.courseName = name;
        this.courseDescription = courseDescription;
    }

    @Override
    public String toString() {
        return String.format("Course[id=%d, name='%s', description='%s']", id, courseName, courseDescription);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return courseName;
    }

    public void setName(String name) {
        this.courseName = name;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = (37 * result) + id;
        return result;
    }
}
