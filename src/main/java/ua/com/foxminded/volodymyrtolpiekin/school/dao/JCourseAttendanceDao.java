package ua.com.foxminded.volodymyrtolpiekin.school.dao;

import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.volodymyrtolpiekin.school.models.CourseAttendance;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class JCourseAttendanceDao implements CourseAttendanceDAO{
    public static final String FIND_BY_COURSE_ID = "select ca from CourseAttendance ca where ca.courseId = :courseId";
    public static final String FIND_BY_STUDENT_ID = "select ca from CourseAttendance ca where ca.studentId = :studentId";
    public static final String GET_ALL = "select c from Course c";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<CourseAttendance> findById(int courseAttendanceId) {
        CourseAttendance courseAttendance = entityManager.find(CourseAttendance.class, courseAttendanceId);
        return Optional.ofNullable(courseAttendance);
    }

    @Transactional
    @Override
    public List<CourseAttendance> getStudentsByCourseId(int courseId) {
        return entityManager.createQuery(FIND_BY_COURSE_ID, CourseAttendance.class)
                .setParameter("courseId", courseId)
                .getResultList();
    }

    @Transactional
    @Override
    public List<CourseAttendance> getCoursesByStudentId(int studentId) {
        return entityManager.createQuery(FIND_BY_STUDENT_ID, CourseAttendance.class)
                .setParameter("studentId", studentId)
                .getResultList();
    }

    @Transactional
    @Override
    public void addStudentToCourse(CourseAttendance courseAttendance) {
        entityManager.persist(courseAttendance);
    }

    @Transactional
    @Override
    public void deleteById(int courseAttendanceId) {
        Optional<CourseAttendance> returnedCourseAttendance = findById(courseAttendanceId);
        returnedCourseAttendance.ifPresent(courseAttendance -> entityManager.remove(courseAttendance));
    }
}