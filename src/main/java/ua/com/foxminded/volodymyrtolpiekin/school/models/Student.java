package ua.com.foxminded.volodymyrtolpiekin.school.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int groupId;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    Set<CourseAttendance> attendances;

    public Student() {
    }

    public Student(int id, int groupId, String firstName, String lastName) {
        this.id = id;
        this.groupId = groupId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<CourseAttendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Set<CourseAttendance> attendances) {
        this.attendances = attendances;
    }

    @Override
    public String toString() {
        return String.format("Student[id=%d, groupId=%d, firstName='%s', lastName='%s']",
                id, groupId, firstName, lastName);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = (37 * result) + id;
        return result;
    }
}
