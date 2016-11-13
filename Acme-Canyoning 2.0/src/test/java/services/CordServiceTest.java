package services;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Cord;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CordServiceTest extends AbstractTest {

	@Autowired
	private CordService cordService;

	//19.1 Manage the pieces of equipment that he or she owns. (CORD)
	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE
	// ----------------------------------------------------
	// creado exitosamente
	@Test
	public void testCreateCord1() {
		authenticate("organiser1");
		int sizeBefore = cordService.findAll().size();
		Cord cord = cordService.create();
		cord.setName("Name test 1");
		cord.setMake("Make test 1");
		cord.setModel("https://images-na.ssl-images-amazon.com/images/I/91aUzgUs9iL._SL1500_.jpg");
		cord.setDescription("Description Test 1");
		cord.setLength(25.0);
		cord.setMaximumWeight(250.0);

		cordService.save(cord);
		int sizeAfter = cordService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE
	// ----------------------------------------------------
	// cord campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void testCreateCordNegative1() {
		authenticate("organiser1");

		Cord cord = cordService.create();
		cord.setName("");
		cord.setMake("");
		cord.setModel("https://images-na.ssl-images-amazon.com/images/I/91aUzgUs9iL._SL1500_.jpg");
		cord.setDescription("Description Test 1");
		cord.setLength(25.0);
		cord.setMaximumWeight(250.0);
		cordService.save(cord);
		unauthenticate();
	}

	// cord con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void testCreateCordNegative2() {
		authenticate("admin");
		int sizeBefore = cordService.findAll().size();
		Cord cord = cordService.create();
		cord.setName("Name test 1");
		cord.setMake("Make test 1");
		cord.setModel("https://images-na.ssl-images-amazon.com/images/I/91aUzgUs9iL._SL1500_.jpg");
		cord.setDescription("Description Test 1");
		cord.setLength(25.0);
		cord.setMaximumWeight(250.0);

		cordService.save(cord);
		int sizeAfter = cordService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);
		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES EDITION
	// ----------------------------------------------------

	// editado correctamente
	@Test
	public void editCord1() {

		authenticate("organiser1");
		String edit = "Cord 1";
		String edit2 = "name edit";
		Cord cord = cordService.findOne(93);
		Assert.isTrue(cord.getName().equals(edit));
		cord.setName("name edit");
		cordService.save(cord);
		Assert.isTrue(cord.getName().equals(edit2));

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES EDITION
	// ----------------------------------------------------
	// Edicion campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void editCord2() {

		authenticate("organiser1");

		Cord cord = cordService.findOne(93);
		cord.setName("");
		cordService.save(cord);

		unauthenticate();
	}

	// editar con actores no autentificdo
	@Test(expected = IllegalArgumentException.class)
	public void editCord3() {

		authenticate("");

		Cord cord = cordService.findOne(93);
		Assert.isTrue(cord.getName() == "Cord 1");
		cord.setName("name edit");
		cordService.save(cord);
		Assert.isTrue(cord.getName() == "name edit");

		unauthenticate();
	}

	// Listing requirement 1

	@Test
	public void testFindCord() {
		Collection<Cord> cords = cordService.findAll();
		Assert.isTrue(cords.size() == 4);
	}

	// Edition requirement 1
	@Test
	public void editionCord1() {

		authenticate("organiser1");

		Cord cord = cordService.findOne(93);
		Assert.isTrue(cord.getLength() == 15.24);
		cord.setLength(25.0);
		cordService.save(cord);
		Assert.isTrue(cord.getLength() == 25.0);

		unauthenticate();
	}

	// Edition requirement 2
	@Test
	public void editionCord2() {

		authenticate("organiser1");

		Cord cord = cordService.findOne(93);
		Assert.isTrue(cord.getMaximumWeight() == 200.0);
		cord.setMaximumWeight(25.0);
		cordService.save(cord);
		Assert.isTrue(cord.getMaximumWeight() == 25.0);

		unauthenticate();
	}

}
