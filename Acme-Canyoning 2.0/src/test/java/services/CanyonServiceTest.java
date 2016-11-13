package services;

import java.util.Collection;
import java.util.LinkedList;

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
import domain.Canyon;
import domain.GPSCoordinates;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CanyonServiceTest extends AbstractTest {

	@Autowired
	private CanyonService canyonService;

	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE
	// ----------------------------------------------------
	// A user who is authenticated as an administrator
	// 10.2 Manage the canyons that he or shes registered to the system.
	// creado exitosamente
	@Test
	public void testCreateCanyonAsAdministrator() {
		authenticate("admin");
		Canyon canyon = canyonService.create();
		canyon.setName("titulo 1");
		canyon.setRoute("https://www.google.es/maps/place/Can%C3%B3n+do+Sil,+27460,+Lugo/@42.4096622,-7.6321641,18z/data=!4m2!3m1!1s0xd30056b4a5bf867:0xc27f74853f84e20c");

		GPSCoordinates gpsCoordinates = new GPSCoordinates();
		gpsCoordinates.setAltitude(20.3);
		gpsCoordinates.setLatitude(-2.0);
		gpsCoordinates.setLongitude(73.10);
		Collection<String> pictures = new LinkedList<String>();
		pictures.add("http://www.turismo.gal/imaxes/mdaw/mday/~edisp/~extract/TG058024~1~staticrendition/tg_carrusel_cabecera_grande.jpg");
		canyon.setPictures(pictures);

		canyon.setDifficultyLevel(3);
		canyon.setGpsCoordinates(gpsCoordinates);
		canyon.setDescription("Descripcion 1");
		canyonService.save(canyon);
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE
	// ----------------------------------------------------
	// canyon campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void testCreateCanyonAsAdministratorNegative1() {
		authenticate("admin");
		Canyon canyon = canyonService.create();
		canyon.setName("");
		canyon.setRoute("");

		GPSCoordinates gpsCoordinates = new GPSCoordinates();
		gpsCoordinates.setAltitude(20.3);
		gpsCoordinates.setLatitude(-2.0);
		gpsCoordinates.setLongitude(73.10);
		Collection<String> pictures = new LinkedList<String>();
		pictures.add("http://www.turismo.gal/imaxes/mdaw/mday/~edisp/~extract/TG058024~1~staticrendition/tg_carrusel_cabecera_grande.jpg");
		canyon.setPictures(pictures);

		canyon.setDifficultyLevel(3);
		canyon.setGpsCoordinates(gpsCoordinates);
		canyon.setDescription("");
		canyonService.save(canyon);
		unauthenticate();
	}

	// canyon con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void testCreateCanyonAsAdministratorNegative2() {
		authenticate("customer1");
		Canyon canyon = canyonService.create();
		canyon.setName("titulo 1");
		canyon.setRoute("https://www.google.es/maps/place/Can%C3%B3n+do+Sil,+27460,+Lugo/@42.4096622,-7.6321641,18z/data=!4m2!3m1!1s0xd30056b4a5bf867:0xc27f74853f84e20c");

		GPSCoordinates gpsCoordinates = new GPSCoordinates();
		gpsCoordinates.setAltitude(20.3);
		gpsCoordinates.setLatitude(-2.0);
		gpsCoordinates.setLongitude(73.10);
		Collection<String> pictures = new LinkedList<String>();
		pictures.add("http://www.turismo.gal/imaxes/mdaw/mday/~edisp/~extract/TG058024~1~staticrendition/tg_carrusel_cabecera_grande.jpg");
		canyon.setPictures(pictures);

		canyon.setDifficultyLevel(3);
		canyon.setGpsCoordinates(gpsCoordinates);
		canyon.setDescription("Descripcion 1");
		canyonService.save(canyon);
		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Eliminado correctamente

	@Test
	public void deletCanyon() {
		authenticate("admin");
		canyonService.delete(canyonService.findOne(97));
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Lo intenta un customer eliminar

	@Test(expected = IllegalArgumentException.class)
	public void deleteCanyon2() {
		authenticate("customer1");
		canyonService.delete(canyonService.findOne(97));
		unauthenticate();
	}

	// Eliminamos un canyon que no es canyonId

	@Test(expected = IllegalArgumentException.class)
	public void deleteCanyon3() {
		authenticate("admin");
		canyonService.delete(canyonService.findOne(98465));
		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES EDITION
	// ----------------------------------------------------

	// editado correctamente
	@Test
	public void editCanyon1() {

		authenticate("admin");

		Canyon canyon = canyonService.findOne(97);
		canyon.setName("title edit");
		canyon.setDescription("Description edit");

		canyonService.save(canyon);

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES EDITION
	// ----------------------------------------------------
	// Edicion campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void editCanyon2() {

		authenticate("admin");

		Canyon canyon = canyonService.findOne(97);

		canyon.setName("");
		canyon.setDescription("Description edit");

		canyonService.save(canyon);

		unauthenticate();
	}

	// editar con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void editCanyon3() {

		authenticate("customer1");

		Canyon canyon = canyonService.findOne(97);

		canyon.setName("Titulo 1");
		canyon.setDescription("Description edit");

		canyonService.save(canyon);

		unauthenticate();
	}

	// 4. A user who is not authenticated must be able to:
	// 1. Navigate through the canyons
	@Test
	public void testFindCanyons() {
		Collection<Canyon> canyons = canyonService.findAll();
		Assert.isTrue(canyons.size() == 6);
	}

	// Edition requirement 1

	@Test
	public void editionCanyon1() {

		authenticate("admin");

		Canyon canyon = canyonService.findOne(97);
		canyon.setName("title edition 1");
		canyon.setDifficultyLevel(9);

		canyonService.save(canyon);

		unauthenticate();
	}

	// Edition requirement 2

	@Test
	public void editionCanyon2() {

		authenticate("admin");

		Canyon canyon = canyonService.findOne(101);

		canyon.setName("title edition 2");
		canyon.setDescription("Description edition 2");

		canyonService.save(canyon);

		unauthenticate();
	}

}