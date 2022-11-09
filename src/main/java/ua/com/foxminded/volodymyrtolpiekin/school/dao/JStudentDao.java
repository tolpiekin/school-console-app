package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class JStudentDao implements StudentDAO {
    public static final String FIND_BY_NAME = "select s from Student s where s.lastName = :lastNameName";
    public static final String GET_ALL = "select s from Student s";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Optional<Student> findById(int studentId) {
            Student student = entityManager.find(Student.class, studentId);
            return Optional.ofNullable(student);
    }

    @Transactional
    @Override
    public Optional<Student> findByName(String lastName) {
            Student student = entityManager.createQuery(FIND_BY_NAME, Student.class)
                    .setParameter("lastName", lastName)
                    .getSingleResult();
            return Optional.ofNullable(student);
    }

    @Transactional
    @Override
    public List<Student> getAll() {
            return entityManager.createQuery(GET_ALL, Student.class)
                    .getResultList();
    }

    @Transactional
    @Override
    public Optional<Student> addItem(Student student) {
            entityManager.persist(student);
            return findById(student.getStudentId());
    }

    @Transactional
    @Override
    public Optional<Student> updateItem(Student student) {
        entityManager.merge(student);
        return findById(student.getStudentId());
    }

    @Transactional
    @Override
    public void deleteById(int studentId) {
        Optional<Student> returnedStudent = findById(studentId);
        returnedStudent.ifPresent(entityManager::remove);
    }
}
