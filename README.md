Task 2.5 Hibernate
Assignment
The Hibernate should be used as a provider that implements JPA specification, the Service layer should use and depend on the JPA interfaces, not the Hibernate ones.

Add the Hibernate support to your project
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
Enrich your model with JPA annotations Rewrite the DAO layer. Use Hibernate instead of Spring JDBC.
Example:

@Entity
@Table(name = "courses")
public class Course {

        @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
                
	@Column(name = "COURSE_NAME")
	private String courseName;

	@Column(name = "COURSE_DESCRIPTION")
	private String courseDescription;

// constructors, getters, setters, etc

}
Rewrite your DAO to use EntityManager instead of JDBCTemplate
Example:

package com.foxminded.spring.console.dao.jpa;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class JPACourseDao implements CourseDao {

    public static final String FIND_BY_NAME = "select c from Course c where c.courseName = :courseName";

    private final EntityManager em;

    public JPACourseDao(EntityManager em) {
        this.em = em;
    }

    @Override
    Optional<Course> findByName(String name) {
        Course course = em.createQuery(FIND_BY_NAME, Course.class)
                .setParameter("courseName", name)
                .getSingleResult();
        return Optional.ofNullable(course);
    }

// rest of code

}
Important:

Your database structure shouldn’t be changed

Your tests should work with the new dao with minimal changes