package ua.com.foxminded.volodymyrtolpiekin.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.JpaCourseDao;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.JpaGroupDao;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.JpaStudentDao;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Course;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Group;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;
import ua.com.foxminded.volodymyrtolpiekin.school.service.CourseServiceImpl;
import ua.com.foxminded.volodymyrtolpiekin.school.service.GroupServiceImpl;
import ua.com.foxminded.volodymyrtolpiekin.school.service.StudentServiceImpl;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
class SchoolApplicationTests {
	@Autowired
	StudentServiceImpl studentServiceImpl;
	@Autowired
	CourseServiceImpl courseServiceImpl;
	@Autowired
	GroupServiceImpl groupServiceImpl;
	@MockBean
	private JpaStudentDao jpaStudentDao;
	@MockBean
	private JpaCourseDao jpaCourseDao;
	@MockBean
	private JpaGroupDao jpaGroupDao;

	@MockBean DatabaseStartup databaseStartup;

	@MockBean Menu menu;

	@Test
	@DisplayName("Test findStudentById  Success")
	void testFindStudentById() {
		Student student = new Student(1, 1, "John", "Doe");
		doReturn(Optional.of(student)).when(jpaStudentDao).findById(1);
		Optional<Student> returnedStudent = studentServiceImpl.findById(1);
		Assertions.assertTrue(returnedStudent.isPresent(), "Student was not found");
		Assertions.assertSame(returnedStudent.get(), student, "The student returned was not the same as the mock");
	}

	@Test
	@DisplayName("Test findStudentById Not Found")
	void testFindStudentByIdNotFound() {
		doReturn(Optional.empty()).when(jpaStudentDao).findById(1);
		Optional<Student> returnedStudent = studentServiceImpl.findById(1);
		Assertions.assertFalse(returnedStudent.isPresent(), "Student should not be found");
	}

	@Test
	@DisplayName("Test getAllStudents")
	void testgetAllStudent() {
		Student student1 = new Student(1, 1, "John1", "Doe1");
		Student student2 = new Student(1, 2, "John2", "Doe2");
		doReturn(Arrays.asList(student1, student2)).when(jpaStudentDao).findAll();
		List<Student> students = jpaStudentDao.findAll();
		Assertions.assertEquals(2, students.size(), "getAll should return 2 students");
	}

	@Test
	@DisplayName("Test add student")
	void testAddStudent() {
		Student student = new Student(1, 1, "John", "Doe");
		doReturn(Optional.of(student)).when(jpaStudentDao).findById(1);
		Optional<Student> returnedStudent = studentServiceImpl.addStudent(student);
		Assertions.assertNotNull(returnedStudent.isPresent(), "The saved student should not be null");
		Assertions.assertSame(returnedStudent.get(), student, "Should be the same student");
	}

	@Test
	@DisplayName("Test findCourseById Success")
	void testFindCourseById() {
		Course course = new Course(1, "math", "Math is the mother and the queen of all the sciences");
		doReturn(Optional.of(course)).when(jpaCourseDao).findById(1);
		Optional<Course> returnedCourse = courseServiceImpl.findById(1);
		Assertions.assertTrue(returnedCourse.isPresent(), "Course was not found");
		Assertions.assertSame(returnedCourse.get(), course, "The course returned was not the same as the mock");
	}

	@Test
	@DisplayName("Test findCourseById Not Found")
	void testFindCourseByIdNotFound() {
		doReturn(Optional.empty()).when(jpaCourseDao).findById(1);
		Optional<Course> returnedCourse = courseServiceImpl.findById(1);
		Assertions.assertFalse(returnedCourse.isPresent(), "Course shouldn't not found");
	}

	@Test
	@DisplayName("Test getAllCourses")
	void testgetAllCourses() {
		Course course1 = new Course(1, "math", "Math is the mother and the queen of all the sciences");
		Course course2 = new Course(2, "history", "The nation not knowing own history is doomed to repat.");
		doReturn(Arrays.asList(course1, course2)).when(jpaCourseDao).findAll();
		List<Course> courses = jpaCourseDao.findAll();
		Assertions.assertEquals(2, courses.size(), "getAll should return 2 courses");
	}

	@Test
	@DisplayName("Test add course")
	void testAddCourse() {
		Course course = new Course(1, "math", "2+2=?");
		doReturn(Optional.of(course)).when(jpaCourseDao).findById(1);
		Optional<Course> returnedCourse = courseServiceImpl.addCourse(course);
		Assertions.assertNotNull(returnedCourse.isPresent(), "The saved student should not be null");
		Assertions.assertSame(returnedCourse.get(), course, "Should be the same student");
	}

	@Test
	@DisplayName("Test findGroupById Success")
	void testFindGroupById() {
		Group group = new Group(1, "gg-dsghj");
		doReturn(Optional.of(group)).when(jpaGroupDao).findById(1);
		Optional<Group> returnedGroup = groupServiceImpl.findById(1);
		Assertions.assertTrue(returnedGroup.isPresent(), "Group was not found");
		Assertions.assertSame(returnedGroup.get(), group, "The group returned was not the same as the mock");
	}

	@Test
	@DisplayName("Test findGroupById Not Found")
	void testFindGroupByIdNotFound() {
		doReturn(Optional.empty()).when(jpaGroupDao).findById(1);
		Optional<Group> returnedGroup = groupServiceImpl.findById(1);
		Assertions.assertFalse(returnedGroup.isPresent(), "Group should not be found");
	}

	@Test
	@DisplayName("Test getAllGroups")
	void testGetAllGroups() {
		Group group1 = new Group(1, "gg-dsghj");
		Group group2 = new Group(2, "hh-dsgfg");
		doReturn(Arrays.asList(group1, group2)).when(jpaGroupDao).findAll();
		List<Group> groups = jpaGroupDao.findAll();
		Assertions.assertEquals(2, groups.size(), "getAll should return 2 groups");
	}

	@Test
	@DisplayName("Test add group")
	void testAddGroup() {
		Group group = new Group(1, "df-dfsdoe");
		doReturn(Optional.of(group)).when(jpaGroupDao).findById(1);
		Optional<Group> returnedGroup = groupServiceImpl.addGroup(group);
		Assertions.assertNotNull(returnedGroup.isPresent(), "The saved group should not be null");
		Assertions.assertSame(returnedGroup.get(), group, "Should be the same group");
	}
}