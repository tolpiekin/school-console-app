package com.foxminded.volodymyrtolpiekin.school.dao;

import com.foxminded.volodymyrtolpiekin.school.models.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentDAO implements DAO<Student> {

    private List<Student> students = new ArrayList<>();
    @Override
    public Optional<Student> get(int id) {
        return Optional.ofNullable(students.get(id));
    }

    @Override
    public Optional<Student> findByName(String name) {
        for(Student student: students) {
            if((student.getFirstName() + " " + student.getLastName()).equals(name)){
                return Optional.ofNullable(student);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Student> getAll() {
        return students;
    }

    @Override
    public void create(Student student) {
        students.add(student);
    }

    @Override
    public void update(Student student) {
        students.set(student.getId(), student);
    }

    @Override
    public void delete(int id) {
        students.remove(id);
    }
}
