package ua.com.foxminded.volodymyrtolpiekin.school.models;

import java.util.Objects;

public class Course {
    private int id;
    private String name;
    private String description;

    public Course() {
    }

    public Course(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Course[id=%d, name='%s', description='%s']", id, name, description);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
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

    public void setDescription(String description) {
        this.description = description;
    }
}
