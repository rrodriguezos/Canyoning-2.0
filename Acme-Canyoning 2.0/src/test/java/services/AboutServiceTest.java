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
import domain.About;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AboutServiceTest extends AbstractTest {

	@Autowired
	private AboutService aboutService;

	// 4. The system must store an about message for every organiser.
	// About messages are displayed whenever the information about an organiser
	// is displayed.Manage About.
	// Positive
	@Test
	public void testEditAbout1() {
		authenticate("organiser1");
		About about = aboutService.findOne(15);
		about.setMessage("About Test");
		aboutService.save(about);
		unauthenticate();

	}

	// Negative
	// Campos vacios
	@Test(expected = ConstraintViolationException.class)
	public void testCreateAbout2() {
		authenticate("organiser1");
		About about = aboutService.create();
		about.setMessage("");
		aboutService.save(about);
		unauthenticate();

	}

	// Actor no autorizado
	@Test(expected = IllegalArgumentException.class)
	public void testCreateAbout3() {
		authenticate("admin");
		About about = aboutService.create();
		about.setMessage("");
		aboutService.save(about);
		unauthenticate();

	}

	// Listing requirement 1

	@Test
	public void testFindAbout() {
		authenticate("organiser1");
		Collection<About> abouts = aboutService.findAll();
		Assert.isTrue(abouts.size() == 3);
		unauthenticate();
	}

	// Edition requirement 1
	@Test
	public void testEditAbout() {
		authenticate("organiser1");
		About about = aboutService.findOne(15);
		about.setMessage("About Test Editing");
		aboutService.save(about);
		unauthenticate();

	}

}
