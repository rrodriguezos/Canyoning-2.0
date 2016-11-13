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

import domain.Wetsuit;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class WetsuitServiceTest extends AbstractTest {
	@Autowired
	private WetsuitService wetsuitService;
	//19.1 Manage the pieces of equipment that he or she owns. (WETSUIT)
	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE
	// ----------------------------------------------------
	// creado exitosamente
	@Test
	public void testCreateWetsuit1() {
		authenticate("organiser1");
		int sizeBefore = wetsuitService.findAll().size();
		Wetsuit wetsuit = wetsuitService.create();
		wetsuit.setName("Name test 1");
		wetsuit.setMake("Make test 1");
		wetsuit.setModel("https://images-na.ssl-images-amazon.com/images/I/91aUzgUs9iL._SL1500_.jpg");
		wetsuit.setDescription("Description Test 1");
		wetsuit.setMinimumTemperature(25.0);
		wetsuit.setTrousers(true);
		wetsuit.setSizeSleeves("SHORT");

		wetsuitService.save(wetsuit);
		int sizeAfter = wetsuitService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE
	// ----------------------------------------------------
	// wetsuit campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void testCreateWetsuitNegative1() {
		authenticate("organiser1");

		Wetsuit wetsuit = wetsuitService.create();
		wetsuit.setName("");
		wetsuit.setMake("");
		wetsuit.setModel("https://images-na.ssl-images-amazon.com/images/I/91aUzgUs9iL._SL1500_.jpg");
		wetsuit.setDescription("Description Test 1");
		wetsuit.setMinimumTemperature(25.0);
		wetsuit.setTrousers(true);
		wetsuit.setSizeSleeves("SHORT");
		wetsuitService.save(wetsuit);
		unauthenticate();
	}

	// wetsuit con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void testCreateWetsuitNegative2() {
		authenticate("admin");
		int sizeBefore = wetsuitService.findAll().size();
		Wetsuit wetsuit = wetsuitService.create();
		wetsuit.setName("Name test 1");
		wetsuit.setMake("Make test 1");
		wetsuit.setModel("https://images-na.ssl-images-amazon.com/images/I/91aUzgUs9iL._SL1500_.jpg");
		wetsuit.setDescription("Description Test 1");
		wetsuit.setMinimumTemperature(25.0);
		wetsuit.setTrousers(true);
		wetsuit.setSizeSleeves("SHORT");

		wetsuitService.save(wetsuit);
		int sizeAfter = wetsuitService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);
		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES EDITION
	// ----------------------------------------------------

	// editado correctamente
	@Test
	public void editWetsuit1() {

		authenticate("organiser1");
		String edit = "Wetsuit 1";
		String edit2 = "name edit";
		Wetsuit wetsuit = wetsuitService.findOne(89);
		Assert.isTrue(wetsuit.getName().equals(edit));
		wetsuit.setName("name edit");
		wetsuitService.save(wetsuit);
		Assert.isTrue(wetsuit.getName().equals(edit2));

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES EDITION
	// ----------------------------------------------------
	// Edicion campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void editWetsuit2() {

		authenticate("organiser1");

		Wetsuit wetsuit = wetsuitService.findOne(89);
		wetsuit.setName("");
		wetsuitService.save(wetsuit);

		unauthenticate();
	}

	// editar con actores no autentificdo
	@Test(expected = IllegalArgumentException.class)
	public void editWetsuit3() {

		authenticate("");

		Wetsuit wetsuit = wetsuitService.findOne(89);
		Assert.isTrue(wetsuit.getName() == "wetsuit 1");
		wetsuit.setName("name edit");
		wetsuitService.save(wetsuit);
		Assert.isTrue(wetsuit.getName() == "name edit");

		unauthenticate();
	}

	// Listing requirement 1

	@Test
	public void testFindWetsuit() {
		Collection<Wetsuit> wetsuits = wetsuitService.findAll();
		Assert.isTrue(wetsuits.size() == 4);
	}

	// Edition requirement 1
	@Test
	public void editionWetsuit1() {

		authenticate("organiser1");

		Wetsuit wetsuit = wetsuitService.findOne(89);
		Assert.isTrue(wetsuit.getMinimumTemperature() == 14.0);
		wetsuit.setMinimumTemperature(25.0);
		wetsuitService.save(wetsuit);
		Assert.isTrue(wetsuit.getMinimumTemperature() == 25.0);

		unauthenticate();
	}

	// Edition requirement 2
	@Test
	public void editionWetsuit2() {

		authenticate("organiser1");

		Wetsuit wetsuit = wetsuitService.findOne(89);
		Assert.isTrue(wetsuit.getTrousers() == true);
		wetsuit.setTrousers(false);
		wetsuitService.save(wetsuit);
		Assert.isTrue(wetsuit.getTrousers() == false);

		unauthenticate();
	}


}

