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

import domain.Kayak;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class KayakServiceTest extends AbstractTest {
	
	@Autowired
	private KayakService kayakService;
	//19.1 Manage the pieces of equipment that he or she owns. (KAYAK)
	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE
	// ----------------------------------------------------
	// creado exitosamente
	@Test
	public void testCreateKayak1() {
		authenticate("organiser1");
		int sizeBefore = kayakService.findAll().size();
		Kayak kayak = kayakService.create();
		kayak.setName("Name test 1");
		kayak.setMake("Make test 1");
		kayak.setModel("https://images-na.ssl-images-amazon.com/images/I/91aUzgUs9iL._SL1500_.jpg");
		kayak.setDescription("Description Test 1");
		kayak.setLength(25.0);
		kayak.setNumberSeats(2);

		kayakService.save(kayak);
		int sizeAfter = kayakService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE
	// ----------------------------------------------------
	// kayak campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void testCreateKayakNegative1() {
		authenticate("organiser1");

		Kayak kayak = kayakService.create();
		kayak.setName("");
		kayak.setMake("");
		kayak.setModel("https://images-na.ssl-images-amazon.com/images/I/91aUzgUs9iL._SL1500_.jpg");
		kayak.setDescription("Description Test 1");
		kayak.setLength(25.0);
		kayak.setNumberSeats(5);
		kayakService.save(kayak);
		unauthenticate();
	}

	// kayak con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void testCreateKayakNegative2() {
		authenticate("admin");
		int sizeBefore = kayakService.findAll().size();
		Kayak kayak = kayakService.create();
		kayak.setName("Name test 1");
		kayak.setMake("Make test 1");
		kayak.setModel("https://images-na.ssl-images-amazon.com/images/I/91aUzgUs9iL._SL1500_.jpg");
		kayak.setDescription("Description Test 1");
		kayak.setLength(25.0);
		kayak.setNumberSeats(2);

		kayakService.save(kayak);
		int sizeAfter = kayakService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);
		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES EDITION
	// ----------------------------------------------------

	// editado correctamente
	@Test
	public void editKayak1() {

		authenticate("organiser1");
		String edit = "kayak 1";
		String edit2 = "name edit";
		Kayak kayak = kayakService.findOne(85);
		Assert.isTrue(kayak.getName().equals(edit));
		kayak.setName("name edit");
		kayakService.save(kayak);
		Assert.isTrue(kayak.getName().equals(edit2));

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES EDITION
	// ----------------------------------------------------
	// Edicion campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void editKayak2() {

		authenticate("organiser1");

		Kayak kayak = kayakService.findOne(85);
		kayak.setName("");
		kayakService.save(kayak);

		unauthenticate();
	}

	// editar con actores no autentificdo
	@Test(expected = IllegalArgumentException.class)
	public void editKayak3() {

		authenticate("");

		Kayak kayak = kayakService.findOne(85);
		Assert.isTrue(kayak.getName() == "kayak 1");
		kayak.setName("name edit");
		kayakService.save(kayak);
		Assert.isTrue(kayak.getName() == "name edit");

		unauthenticate();
	}

	// Listing requirement 1

	@Test
	public void testFindKayak() {
		Collection<Kayak> kayaks = kayakService.findAll();
		Assert.isTrue(kayaks.size() == 4);
	}

	// Edition requirement 1
	@Test
	public void editionKayak1() {

		authenticate("organiser1");

		Kayak kayak = kayakService.findOne(85);
		Assert.isTrue(kayak.getLength() == 3.0);
		kayak.setLength(25.0);
		kayakService.save(kayak);
		Assert.isTrue(kayak.getLength() == 25.0);

		unauthenticate();
	}

	// Edition requirement 2
	@Test
	public void editionKayak2() {

		authenticate("organiser1");

		Kayak kayak = kayakService.findOne(85);
		Assert.isTrue(kayak.getNumberSeats() == 1);
		kayak.setNumberSeats(8);
		kayakService.save(kayak);
		Assert.isTrue(kayak.getNumberSeats() == 8);

		unauthenticate();
	}


}
