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
import domain.LearningMaterial;
import domain.Module;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LearningMaterialServiceTest extends AbstractTest {
	@Autowired
	private LearningMaterialService learningMaterialService;
	@Autowired
	private ModuleService moduleService;

	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE
	// ----------------------------------------------------
	// creado exitosamente
	@Test
	public void testCreateLearningMaterial1() {

		authenticate("trainer1");
		int sizeBefore = learningMaterialService.findAll().size();
		LearningMaterial learningMaterial = learningMaterialService.create();
		Module module = moduleService.findOne(55);

		learningMaterial.setModule(module);
		learningMaterial.setTitle("titulo 1");
		learningMaterial.setDescription("Descripcion 1");
		learningMaterial
				.setMaterialLink("https://www.youtube.com/watch?v=eiVS_EsXJJw");
		learningMaterialService.save(learningMaterial);
		int sizeAfter = learningMaterialService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE
	// ----------------------------------------------------
	// learningMaterial no URL
	@Test(expected = ConstraintViolationException.class)
	public void testCreateLearningMaterial2() {
		authenticate("trainer1");
		LearningMaterial learningMaterial = learningMaterialService.create();
		Module module = moduleService.findOne(55);

		learningMaterial.setModule(module);
		learningMaterial.setTitle("titulo 1");
		learningMaterial.setDescription("Descripcion 1");
		learningMaterial.setMaterialLink("no es una URL");
		learningMaterialService.save(learningMaterial);

		unauthenticate();
	}

	// crear un learningMaterial con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void testCreateLearningMaterial3() {
		authenticate("admin");
		LearningMaterial learningMaterial = learningMaterialService.create();
		Module module = moduleService.findOne(55);

		learningMaterial.setModule(module);
		learningMaterial.setTitle("titulo 1");
		learningMaterial.setDescription("Descripcion 1");
		learningMaterial.setMaterialLink("no es una URL");
		learningMaterialService.save(learningMaterial);

		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Eliminado correctamente

	@Test
	public void deletLearningMaterial1() {
		authenticate("trainer1");
		int sizeBefore = learningMaterialService.findAll().size();
		LearningMaterial learningMaterial;
		learningMaterial = learningMaterialService.findOne(74);

		learningMaterialService.delete(learningMaterial);
		int sizeAfter = learningMaterialService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore - 1);

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Lo intenta un admin eliminar

	@Test(expected = IllegalArgumentException.class)
	public void deletLearningMaterial2() {
		authenticate("admin");
		LearningMaterial learningMaterial;
		learningMaterial = learningMaterialService.findOne(84);

		learningMaterialService.delete(learningMaterial);

		unauthenticate();
	}

	// Eliminamos un learningMaterial que no es learningMaterialId

	@Test(expected = NullPointerException.class)
	public void deletLearningMaterial3() {
		authenticate("trainer1");
		LearningMaterial learningMaterial;
		learningMaterial = learningMaterialService.findOne(88475);

		learningMaterialService.delete(learningMaterial);

		unauthenticate();
	}

	// Edition requirement 1
	@Test
	public void editionLearningMaterial1() {

		authenticate("trainer1");

		LearningMaterial learninM = learningMaterialService.findOne(74);
		Assert.isTrue(learninM.getTitle().equals("Learning material 1"));
		learninM.setTitle("title test");
		learningMaterialService.save(learninM);
		Assert.isTrue(learninM.getTitle().equals("title test"));

		unauthenticate();
	}

	// Edition requirement 2
	@Test
	public void editionLearningMaterial2() {

		authenticate("trainer1");

		LearningMaterial learninM = learningMaterialService.findOne(74);
		Assert.isTrue(learninM.getMaterialLink().equals(
				"https://www.youtube.com/watch?v=eiVS_EsXJJw"));
		learninM.setTitle("https://www.youtube.com/editado");
		learningMaterialService.save(learninM);
		Assert.isTrue(learninM.getTitle().equals(
				"https://www.youtube.com/editado"));

		unauthenticate();
	}

	// Listing requirement 1

	@Test
	public void testFindLearningMaterial() {
		Collection<LearningMaterial> lms = learningMaterialService.findAll();
		Assert.isTrue(lms.size() == 11);
	}

}
