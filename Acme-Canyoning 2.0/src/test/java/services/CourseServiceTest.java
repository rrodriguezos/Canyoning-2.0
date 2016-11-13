package services;

import java.util.Collection;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Course;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CourseServiceTest extends AbstractTest {
	@Autowired
	private CourseService courseService;

	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE
	// ----------------------------------------------------
	// A user who is authenticated as an trainer
	// 6.1 Manage his or her courses, which includes creating, listing,
	// modifying, or deleting them.
	// creado exitosamente
	@Test
	public void testCreateCourseAsTrainer() {
		authenticate("trainer1");
		int sizeBefore = courseService.findAll().size();
		Course course = courseService.create();
		course.setTitle("Title test");
		course.setBanner("https://www.google.es/maps/place/Can%C3%B3n+do+Sil,+27460,+Lugo/@42.4096622,-7.6321641,18z/data=!4m2!3m1!1s0xd30056b4a5bf867:0xc27f74853f84e20c");
		course.setDescription("Descripcion Test 1");
		courseService.save(course);
		int sizeAfter = courseService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE
	// ----------------------------------------------------
	// course campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void testCreateCourseAsTrainerNegative1() {
		authenticate("trainer1");

		Course course = courseService.create();
		course.setTitle("");
		course.setBanner("");
		course.setDescription("Descripcion Test 1");
		courseService.save(course);

		unauthenticate();
	}

	// course con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void testCreateCourseAsTrainerNegative2() {
		authenticate("customer1");

		Course course = courseService.create();
		course.setTitle("");
		course.setBanner("");
		course.setDescription("Descripcion Test 1");
		courseService.save(course);

		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Eliminado correctamente

	@Test
	public void deletCourse() {
		authenticate("trainer1");
		courseService.delete(courseService.findOne(54));
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Lo intenta un customer eliminar

	@Test(expected = IllegalArgumentException.class)
	public void deleteCourse2() {
		authenticate("customer1");
		courseService.delete(courseService.findOne(54));
		unauthenticate();
	}

	// Eliminamos un course que no es courseId

	@Test(expected = IllegalArgumentException.class)
	public void deleteCourse3() {
		authenticate("trainer1");
		courseService.delete(courseService.findOne(98465));
		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES EDITION
	// ----------------------------------------------------

	// editado correctamente
	@Test
	public void editCourse1() {

		authenticate("trainer1");

		Course course = courseService.findOne(54);
		course.setTitle("title edit");
		course.setDescription("Description edit");

		courseService.save(course);

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES EDITION
	// ----------------------------------------------------
	// Edicion campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void editCourse2() {

		authenticate("trainer1");

		Course course = courseService.findOne(54);

		course.setTitle("");
		course.setDescription("");

		courseService.save(course);

		unauthenticate();
	}

	// editar con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void editCourse3() {

		authenticate("customer1");

		Course course = courseService.findOne(54);

		course.setTitle("Titulo 1");
		course.setDescription("Description edit");

		courseService.save(course);

		unauthenticate();
	}

	// 5. A user who is not authenticated must be able to:
	// 1. Browse the catalogue of courses and navigate to the corresponding
	// trainers.
	@Test
	public void testFindCourses() {
		Collection<Course> courses = courseService.findAll();
		Assert.isTrue(courses.size() == 9);
	}

	// Edition requirement 1

	@Test
	public void editionCourse1() {

		authenticate("trainer1");
		String edit = "Descenso";
		String edit2 = "title edition 1";

		Course course = courseService.findOne(54);
		Assert.isTrue(course.getTitle().equals(edit));
		course.setTitle("title edition 1");
		course.setBanner("http://www.mallorcaverde.es/imagenes/banner-escalada.jpg");

		courseService.save(course);
		Assert.isTrue(course.getTitle().equals(edit2));

		unauthenticate();
	}

	// Edition requirement 2

	@Test
	public void editionCourse2() {

		authenticate("trainer1");
		String edit = "Curso de Descenso";
		String edit2 = "Description edition 1";

		Course course = courseService.findOne(54);
		Assert.isTrue(course.getDescription().equals(edit));
		course.setDescription("Description edition 1");
		course.setBanner("http://www.mallorcaverde.es/imagenes/banner-escalada.jpg");

		courseService.save(course);
		Assert.isTrue(course.getDescription().equals(edit2));

		unauthenticate();
	}

}
