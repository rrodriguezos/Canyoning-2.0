package services;

import java.util.Collection;

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
import domain.Module;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ModuleServiceTest extends AbstractTest {

	@Autowired
	private ModuleService moduleService;
	@Autowired
	private CourseService courseService;

	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE
	// ----------------------------------------------------
	// creado exitosamente
	@Test
	public void testCreateModule1() {

		authenticate("trainer1");
		int sizeBefore = moduleService.findAll().size();
		Module module = moduleService.create();
		Course course = courseService.findOne(54);
		module.setCourse(course);
		module.setOrderModule(5);
		module.setTitle("Module Test");
		moduleService.save(module);
		int sizeAfter = moduleService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE
	// ----------------------------------------------------
	// module campos en blanco
	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testCreateModule2() {
		authenticate("trainer1");
		Module module = moduleService.create();
		Course course = courseService.findOne(54);
		module.setCourse(course);
		module.setOrderModule(5);
		module.setTitle("");
		moduleService.save(module);

		unauthenticate();
	}

	// crear un module con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void testCreateModule3() {
		authenticate("admin");
		Module module = moduleService.create();
		Course course = courseService.findOne(54);
		module.setCourse(course);
		module.setOrderModule(5);
		module.setTitle("Module Test");
		moduleService.save(module);

		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Eliminado correctamente

	@Test
	public void deletModule1() {
		authenticate("trainer1");
		Module module;

		module = moduleService.findOne(55);

		moduleService.delete(module);

		unauthenticate();

	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Lo intenta un admin eliminar

	@Test(expected = IllegalArgumentException.class)
	public void deletModule2() {
		authenticate("admin");
		Module module;

		module = moduleService.findOne(55);

		moduleService.delete(module);

		unauthenticate();
	}

	// Eliminamos un course que no es courseId

	@Test(expected = IllegalArgumentException.class)
	public void deletModule3() {
		authenticate("trainer1");
		Module module;

		module = moduleService.findOne(887954);

		moduleService.delete(module);

		unauthenticate();
	}

	// Listing requirement 1

	@Test
	public void testFindModule() {
		Collection<Module> modules = moduleService.findAll();
		Assert.isTrue(modules.size() == 11);
	}

	// Edition requirement 1
	@Test
	public void editionModule1() {

		authenticate("trainer1");

		Module module = moduleService.findOne(55);
		Assert.isTrue(module.getTitle().equals("Modulo 1"));
		module.setTitle("title test");
		moduleService.save(module);
		Assert.isTrue(module.getTitle().equals("title test"));

		unauthenticate();
	}

	// Edition requirement 2
	@Test
	public void editionModule2() {

		authenticate("trainer1");

		Module module = moduleService.findOne(56);
		Assert.isTrue(module.getOrderModule() == 2);
		module.setOrderModule(7);
		moduleService.save(module);
		Assert.isTrue(module.getOrderModule() == 7);

		unauthenticate();
	}

}
