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
import domain.Curriculum;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CurriculumServiceTest extends AbstractTest {

	@Autowired
	private CurriculumService curriculumService;

	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE
	// ----------------------------------------------------
	// A user who is authenticated as an trainer
	// 9.1 Manage his or her curricula.
	// creado exitosamente
	@Test
	public void testCreateCurriculumAsTrainer() {
		authenticate("trainer1");
		int sizeBefore = curriculumService.findAll().size();
		Curriculum curriculum = curriculumService.create();
		curriculum.setName("Name test");
		curriculum.setEmail("test@gmail.com");
		curriculum.setPhone("923456789");
		curriculumService.save(curriculum);
		int sizeAfter = curriculumService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE
	// ----------------------------------------------------
	// curriculum campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void testCreateCurriculumAsTrainerNegative1() {
		authenticate("trainer1");
		Curriculum curriculum = curriculumService.create();
		curriculum.setName("");
		curriculum.setEmail("");
		curriculum.setPhone("");
		curriculumService.save(curriculum);
		unauthenticate();
	}

	// curriculum con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void testCreateCurriculumAsTrainerNegative2() {
		authenticate("customer1");
		Curriculum curriculum = curriculumService.create();
		curriculum.setName("");
		curriculum.setEmail("");
		curriculum.setPhone("");
		curriculumService.save(curriculum);
		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Eliminado correctamente

	@Test
	public void deletCurriculum() {
		authenticate("trainer1");
		int sizeBefore = curriculumService.findAll().size();
		curriculumService.delete(curriculumService.findOne(36));
		int sizeAfter = curriculumService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore - 1);
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Lo intenta un customer eliminar

	@Test(expected = IllegalArgumentException.class)
	public void deleteCurriculum2() {
		authenticate("customer1");
		curriculumService.delete(curriculumService.findOne(36));
		unauthenticate();
	}

	// Eliminamos un curriculum que no es curriculumId

	@Test(expected = IllegalArgumentException.class)
	public void deleteCurriculum3() {
		authenticate("trainer1");
		curriculumService.delete(curriculumService.findOne(98465));
		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES EDITION
	// ----------------------------------------------------

	// editado correctamente
	@Test
	public void editCurriculum1() {

		authenticate("trainer1");

		Curriculum curriculum = curriculumService.findOne(36);
		Assert.isTrue(curriculum.getName().equals("Bruno"));
		curriculum.setName("Test");
		curriculumService.save(curriculum);
		Assert.isTrue(curriculum.getName().equals("Test"));

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES EDITION
	// ----------------------------------------------------
	// Edicion campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void editCurriculum2() {

		authenticate("trainer1");

		Curriculum curriculum = curriculumService.findOne(36);

		curriculum.setName("");
		curriculum.setPhone("");

		curriculumService.save(curriculum);

		unauthenticate();
	}

	// editar con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void editCurriculum3() {

		authenticate("customer1");

		Curriculum curriculum = curriculumService.findOne(36);

		curriculum.setName("Name");
		curriculum.setPhone("Phone edit");

		curriculumService.save(curriculum);

		unauthenticate();
	}

	// List requitement 1
	@Test
	public void testFindCurriculums() {
		Collection<Curriculum> curriculums = curriculumService.findAll();
		Assert.isTrue(curriculums.size() == 6);
	}

	// Edition requirement 1

	@Test
	public void editionCurriculum1() {

		authenticate("trainer1");

		Curriculum curriculum = curriculumService.findOne(36);
		Assert.isTrue(curriculum.getName().equals("Bruno"));
		curriculum.setName("EditName");
		curriculumService.save(curriculum);
		Assert.isTrue(curriculum.getName().equals("EditName"));

		unauthenticate();
	}

	// Edition requirement 2

	@Test
	public void editionCurriculum2() {

		authenticate("trainer1");

		Curriculum curriculum = curriculumService.findOne(37);
		Assert.isTrue(curriculum.getName().equals("Juan"));
		curriculum.setName("JuanEdit");
		curriculumService.save(curriculum);
		Assert.isTrue(curriculum.getName().equals("JuanEdit"));

		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES
	// ----------------------------------------------------
	// A user who is authenticated as an trainer
	// 9.2 Activate a curriculum. Activating a curriculum implies that the
	// current active curriculum,
	// if any, is deactivated.
	@Test
	public void testActivateCurriculum() {
		authenticate("trainer1");
		Curriculum curriculum1;
		Curriculum curriculum2;

		curriculum1 = curriculumService.findOne(37);
		curriculum2 = curriculumService.findOne(36);
		Assert.isTrue(curriculum1.getIsActive() == false);
		Assert.isTrue(curriculum2.getIsActive() == true);

		curriculumService.changeStateCVtoActive(curriculum1);

		Assert.isTrue(curriculum1.getIsActive() == true);
		Assert.isTrue(curriculum2.getIsActive() == false);
		unauthenticate();
	}

}
