package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class JCourseDao implements CourseDAO{
    public static final String FIND_BY_NAME = "select c from Course c where c.courseName = :courseName";
    public static final String GET_ALL = "select c from Course c";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Optional<Course> findById(int courseId) {
        Course course = entityManager.find(Course.class, courseId);
        return Optional.ofNullable(course);
    }

    @Transactional
    @Override
    public Optional<Course> findByName(String name) {
        Course course = entityManager.createQuery(FIND_BY_NAME, Course.class)
                .setParameter("courseName", name)
                .getSingleResult();
        return Optional.ofNullable(course);
    }

    @Transactional
    @Override
    public List<Course> getAll() {
        return entityManager.createQuery(GET_ALL, Course.class)
                .getResultList();
    }

    @Transactional
    @Override
    public Optional<Course> addItem(Course course) {
        entityManager.persist(course);
        return findById(course.getCourseId());
    }

    @Transactional
    @Override
    public Optional<Course> updateItem(Course course) {
        entityManager.merge(course);
        return findById(course.getCourseId());
    }

    @Transactional
    @Override
    public void deleteById(int courseId) {
        Optional<Course> returnedCourse = findById(courseId);
        returnedCourse.ifPresent(entityManager::remove);
    }
}
