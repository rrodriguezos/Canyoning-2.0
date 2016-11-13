package services;

import java.util.Collection;
import java.util.LinkedList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Curriculum;
import domain.Section;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SectionServiceTest extends AbstractTest {
	
	@Autowired
	private SectionService sectionService;
	@Autowired
	private CurriculumService curriculumService;

	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE
	// ----------------------------------------------------
	// creado exitosamente
	@Test
	public void testCreateSection1() {

		authenticate("trainer1");
		int sizeBefore = sectionService.findAll().size();
		Section section = sectionService.create();
		Curriculum curriculum = curriculumService.findOne(36);
		section.setCurriculum(curriculum);
		Collection<String> attach = new LinkedList<String>();
		String attac1 = "https://www.youtube.com/watch?v=eiVS_EsXJJw";
		attach.add(attac1);
		section.setAttachments(attach);
		section.setContent("Content Test");
		section.setTitle("Section Test");
		sectionService.save(section);
		int sizeAfter = sectionService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);

		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE
	// ----------------------------------------------------
	// section campos en blanco
	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testCreateSection2() {
		authenticate("trainer1");
		int sizeBefore = sectionService.findAll().size();
		Section section = sectionService.create();
		Curriculum curriculum = curriculumService.findOne(36);
		section.setCurriculum(curriculum);
		Collection<String> attach = new LinkedList<String>();
		String attac1 = "https://www.youtube.com/watch?v=eiVS_EsXJJw";
		attach.add(attac1);
		section.setAttachments(attach);
		section.setContent("");
		section.setTitle("");
		sectionService.save(section);
		int sizeAfter = sectionService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);

		unauthenticate();
	}

	// crear un section con actores no autorizados
	@Test(expected = IllegalArgumentException.class)
	public void testCreateSection3() {
		authenticate("admin");
		int sizeBefore = sectionService.findAll().size();
		Section section = sectionService.create();
		Curriculum curriculum = curriculumService.findOne(36);
		section.setCurriculum(curriculum);
		Collection<String> attach = new LinkedList<String>();
		String attac1 = "https://www.youtube.com/watch?v=eiVS_EsXJJw";
		attach.add(attac1);
		section.setAttachments(attach);
		section.setContent("Content Test");
		section.setTitle("Section Test");
		sectionService.save(section);
		int sizeAfter = sectionService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);

		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Eliminado correctamente

	@Test
	public void deletSection1() {
		authenticate("trainer1");
		Section section;
		int sizeBefore = sectionService.findAll().size();
		section = sectionService.findOne(53);

		sectionService.delete(section);
		int sizeAfter = sectionService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore - 1);
		unauthenticate();

	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES DELETE
	// ----------------------------------------------------
	// Lo intenta un admin eliminar

	@Test(expected = IllegalArgumentException.class)
	public void deletSection2() {
		authenticate("admin");
		Section section;

		section = sectionService.findOne(53);

		sectionService.delete(section);

		unauthenticate();
	}

	// Eliminamos un section que no es sectionId

	@Test(expected = IllegalArgumentException.class)
	public void deletSection3() {
		authenticate("trainer1");
		Section section;

		section = sectionService.findOne(0);

		sectionService.delete(section);

		unauthenticate();
	}

	// Listing requirement 1

	@Test
	public void testFindSection() {
		Collection<Section> sections = sectionService.findAll();
		Assert.isTrue(sections.size() == 12);
	}

	// Edition requirement 1
	@Test
	public void editionSection1() {

		authenticate("trainer1");

		Section section = sectionService.findOne(42);
		Assert.isTrue(section.getTitle().equals("Section 1"));
		section.setTitle("title test");
		sectionService.save(section);
		Assert.isTrue(section.getTitle().equals("title test"));

		unauthenticate();
	}

	// Edition requirement 2
	@Test
	public void editionSection2() {

		authenticate("trainer1");

		Section section = sectionService.findOne(42);
		Assert.isTrue(section.getContent().equals("Contents 1"));
		section.setContent("Contents test");
		sectionService.save(section);
		Assert.isTrue(section.getContent().equals("Contents test"));

		unauthenticate();
	}


}
