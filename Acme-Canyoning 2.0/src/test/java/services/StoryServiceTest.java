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
import domain.Story;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class StoryServiceTest extends AbstractTest {

	@Autowired
	private StoryService storyService;

	@Autowired
	private CanyonService canyonService;

	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE
	// ----------------------------------------------------
	// creado exitosamente
	@Test
	public void testCreateStory1() {
		authenticate("admin");
		int sizeBefore = storyService.findAll().size();
		Story story = storyService.create();
		story.setTitle("titulo 1");
		story.setAuthorName("Pepe");
		story.setText("Text test");
		Collection<String> resources = new LinkedList<String>();
		String reso1 = "http://www.koolrent.com/upload/model/6/15-kayak.jpg";
		resources.add(reso1);
		story.setResourcesList(resources);

		Canyon canyon = canyonService.findOne(97);
		story.setCanyon(canyon);

		storyService.save(story);
		int sizeAfter = storyService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE
	// ----------------------------------------------------
	// story campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void testCreateStoryNegative1() {
		authenticate("admin");
		Story story = storyService.create();
		story.setTitle("");
		story.setAuthorName("");
		story.setText("");
		Collection<String> resources = new LinkedList<String>();
		String reso1 = "http://www.koolrent.com/upload/model/6/15-kayak.jpg";
		resources.add(reso1);
		story.setResourcesList(resources);

		Canyon canyon = canyonService.findOne(97);
		story.setCanyon(canyon);

		storyService.save(story);
		unauthenticate();
	}

	// story con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void testCreateStoryNegative2() {
		authenticate("trainer1");
		Story story = storyService.create();
		story.setTitle("");
		story.setAuthorName("");
		story.setText("");
		Collection<String> resources = new LinkedList<String>();
		String reso1 = "http://www.koolrent.com/upload/model/6/15-kayak.jpg";
		resources.add(reso1);
		story.setResourcesList(resources);

		Canyon canyon = canyonService.findOne(97);
		story.setCanyon(canyon);

		storyService.save(story);
		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES EDITION
	// ----------------------------------------------------

	// editado correctamente
	@Test
	public void editStory1() {

		authenticate("admin");

		Story story = storyService.findOne(107);
		Assert.isTrue(story.getTitle().equals("Historia del desfiladero 1"));
		story.setTitle("Title edition");
		story.setText("Text edition");

		storyService.save(story);
		Assert.isTrue(story.getTitle().equals("Title edition"));

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES EDITION
	// ----------------------------------------------------
	// Edicion campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void editStory2() {

		authenticate("admin");

		Story story = storyService.findOne(108);

		story.setTitle("");
		story.setText("");

		storyService.save(story);

		unauthenticate();
	}

	// editar con actores no autentificdo
	@Test(expected = IllegalArgumentException.class)
	public void editStory3() {

		authenticate("trainer1");

		Story story = storyService.findOne(114);

		story.setTitle("Title edition");
		story.setText("Text edition");

		storyService.save(story);

		unauthenticate();
	}

	// Listing requirement 1

	@Test
	public void testFindStory() {
		Collection<Story> activities = storyService.findAll();
		Assert.isTrue(activities.size() == 4);
	}

	// Edition requirement 1
	@Test
	public void editionStory1() {

		authenticate("admin");

		Story story = storyService.findOne(107);
		Assert.isTrue(story.getTitle().equals("Historia del desfiladero 1"));
		Assert.isTrue(story.getText().equals("Nos lo pasamos muy bien"));
		story.setTitle("Title edition");
		story.setText("Text edition");

		storyService.save(story);
		Assert.isTrue(story.getTitle().equals("Title edition"));
		Assert.isTrue(story.getText().equals("Text edition"));

		unauthenticate();
	}

	// Edition requirement 2
	@Test
	public void editionStory2() {

		authenticate("admin");

		Story story = storyService.findOne(108);

		Assert.isTrue(story.getTitle().equals("Historia del desfiladero 2"));
		Assert.isTrue(story.getText().equals("Nos lo pasamos muy regular"));
		story.setTitle("Title edition");
		story.setText("Text edition");

		storyService.save(story);
		Assert.isTrue(story.getTitle().equals("Title edition"));
		Assert.isTrue(story.getText().equals("Text edition"));

		unauthenticate();
	}

}
