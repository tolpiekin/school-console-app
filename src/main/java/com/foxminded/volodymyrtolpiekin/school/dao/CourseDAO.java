package com.foxminded.volodymyrtolpiekin.school.dao;

import com.foxminded.volodymyrtolpiekin.school.models.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class CourseDAO implements DAO<Course>{

    private List<Course> courses = new ArrayList<>();
    @Override
    public Optional<Course> getById(int id) {
        return Optional.ofNullable(courses.get(id));
    }

    @Override
    public List<Course> getAll() {
        return courses;
    }

    @Override
    public void create(Course course) {
        courses.add(course);
    }

    @Override
    public void update(Course course) {
        courses.set(course.getId(), course);
    }

    @Override
    public void deleteById(int id) {
        courses.remove(id);
    }

    @Override
    public Optional<Course> findByName(String name) {
        for (Course course: courses) {
            if (course.getName().equals(name)) {
                return Optional.ofNullable(course);
            }
        }
        return Optional.empty();
    }
}
