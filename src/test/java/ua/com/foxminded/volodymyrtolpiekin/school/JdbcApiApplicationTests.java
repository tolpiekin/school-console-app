package ua.com.foxminded.volodymyrtolpiekin.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.com.foxminded.volodymyrtolpiekin.school.models.Student;
import ua.com.foxminded.volodymyrtolpiekin.school.dao.StudentDAO;
import ua.com.foxminded.volodymyrtolpiekin.school.service.StudentService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(classes = JdbcApiApplication.class)
class JdbcApiApplicationTests {
	@Autowired
	StudentService service;

	@MockBean
	private StudentDAO repository;

	@Test
	@DisplayName("Test findById Success")
	void testFindById() {
		Student student = new Student(1, 1, "John", "Doe");
		doReturn(Optional.of(student)).when(repository).findById(1);

		Optional<Student> returnedStudent = service.findById(1);

		Assertions.assertTrue(returnedStudent.isPresent(), "Widget was not found");
		Assertions.assertSame(returnedStudent.get(), student, "The widget returned was not the same as the mock");
	}

	@Test
	@DisplayName("Test findById Not Found")
	void testFindByIdNotFound() {
		doReturn(Optional.empty()).when(repository).findById(1);

		Optional<Student> returnedStudent = service.findById(1);

		Assertions.assertFalse(returnedStudent.isPresent(), "Student should not be found");
	}

	@Test
	@DisplayName("Test getAll")
	void testFindAll() {
		Student student1 = new Student(1, 1, "John1", "Doe1");
		Student student2 = new Student(1, 2, "John2", "Doe2");
		doReturn(Arrays.asList(student1, student2)).when(repository).getAll();

		// Execute the service call
		List<Student> students = repository.getAll();

		// Assert the response
		Assertions.assertEquals(2, students.size(), "getAll should return 2 students");
	}

	@Test
	@DisplayName("Test add student")
	void testSave() {
		Student student = new Student(1, 1, "John", "Doe");
		doReturn(student).when(repository).addItem(any());

		Optional<Student> returnedStudent = repository.addItem(student);

		Assertions.assertNotNull(returnedStudent, "The saved student should not be null");
		Assertions.assertEquals(returnedStudent.get(), student, "Should be the same student");
	}

}