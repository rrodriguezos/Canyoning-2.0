package services;

import java.util.Collection;
import java.util.Date;

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
import domain.Comment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CommentServiceTest extends AbstractTest {

	@Autowired
	private CommentService commentService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private CanyonService canyonService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private CustomerCommentService customerCommentService;

	@Autowired
	private CourseService courseService;

	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE CANYON
	// ----------------------------------------------------
	// 7.1 Comment on other actors, activities, or canyons.
	// creado exitosamente
	@Test
	public void testCreateCommentCanyon1() {
		authenticate("admin");
		Comment comment = new Comment();
		comment.setActor(actorService.findByPrincipal());
		comment.setCommentable(canyonService.findAll().iterator().next());
		comment.setStars(3);
		comment.setMoment(new Date(System.currentTimeMillis() - 1000));
		comment.setTitle("Create comment canyon");
		comment.setBody("Comment on Canyon");
		commentService.save(comment);
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE CANYON
	// ----------------------------------------------------
	// comment campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void testCreateCommentCanyon2() {
		authenticate("admin");
		Comment comment = new Comment();
		comment.setActor(actorService.findByPrincipal());
		comment.setCommentable(canyonService.findAll().iterator().next());
		comment.setStars(3);
		comment.setMoment(new Date(System.currentTimeMillis() - 1000));
		comment.setTitle("");
		comment.setBody("");
		commentService.save(comment);
		unauthenticate();
	}

	// actor inexistente
	@Test(expected = IllegalArgumentException.class)
	public void testCreateCommentCanyon3() {
		authenticate("none");
		Comment comment = new Comment();
		comment.setActor(actorService.findByPrincipal());
		comment.setCommentable(canyonService.findAll().iterator().next());
		comment.setStars(3);
		comment.setMoment(new Date(System.currentTimeMillis() - 1000));
		comment.setTitle("Create comment canyon");
		comment.setBody("Comment on Canyon");
		commentService.save(comment);
		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE ACTIVITY
	// ----------------------------------------------------

	// creado exitosamente
	@Test
	public void testCreateCommentActivity1() {
		authenticate("customer1");
		Comment comment = new Comment();
		comment.setActor(actorService.findByPrincipal());
		comment.setCommentable(activityService.findAll().iterator().next());
		comment.setStars(1);
		comment.setMoment(new Date(System.currentTimeMillis() - 1000));
		comment.setTitle("Create comment activity");
		comment.setBody("Comment on activity");
		commentService.save(comment);
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE ACTIVITY
	// ----------------------------------------------------
	// comment campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void testCreateCommentActivity2() {
		authenticate("customer1");
		Comment comment = new Comment();
		comment.setActor(actorService.findByPrincipal());
		comment.setCommentable(activityService.findAll().iterator().next());
		comment.setStars(1);
		comment.setMoment(new Date(System.currentTimeMillis() - 1000));
		comment.setTitle("");
		comment.setBody("");
		commentService.save(comment);
		unauthenticate();
	}

	// actor inexistente
	@Test(expected = IllegalArgumentException.class)
	public void testCreateCommentActivity3() {
		authenticate("none");
		Comment comment = new Comment();
		comment.setActor(actorService.findByPrincipal());
		comment.setCommentable(activityService.findAll().iterator().next());
		comment.setStars(1);
		comment.setMoment(new Date(System.currentTimeMillis() - 1000));
		comment.setTitle("Create comment activity");
		comment.setBody("Comment on activity");
		commentService.save(comment);
		unauthenticate();
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE Actor
	// ----------------------------------------------------

	// creado exitosamente
	@Test
	public void testCreateCommentActor1() {
		authenticate("customer1");
		Comment comment = new Comment();
		comment.setActor(actorService.findByPrincipal());
		comment.setCommentable(customerCommentService.findAll().iterator()
				.next());
		comment.setStars(1);
		comment.setMoment(new Date(System.currentTimeMillis() - 1000));
		comment.setTitle("Create comment customer");
		comment.setBody("Comment on customer");
		commentService.save(comment);
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE Actor
	// ----------------------------------------------------
	// comment campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void testCreateCommentActor2() {
		authenticate("customer1");
		Comment comment = new Comment();
		comment.setActor(actorService.findByPrincipal());
		comment.setCommentable(customerCommentService.findAll().iterator()
				.next());
		comment.setStars(1);
		comment.setMoment(new Date(System.currentTimeMillis() - 1000));
		comment.setTitle("");
		comment.setBody("");
		commentService.save(comment);
		unauthenticate();
	}

	// actor inexistente
	@Test(expected = IllegalArgumentException.class)
	public void testCreateCommentActor3() {
		authenticate("none");
		Comment comment = new Comment();
		comment.setActor(actorService.findByPrincipal());
		comment.setCommentable(customerCommentService.findAll().iterator()
				.next());
		comment.setStars(1);
		comment.setMoment(new Date(System.currentTimeMillis() - 1000));
		comment.setTitle("Create comment customer");
		comment.setBody("Comment on customer");
		commentService.save(comment);
		unauthenticate();
	}

	// Listing requirement 1

	@Test
	public void testFindComments() {
		Collection<Comment> comments = commentService.findAll();
		Assert.isTrue(comments.size() == 26);
	}

	// ----------------------------------------------------
	// POSITIVE TEST CASES CREATE Courses
	// ----------------------------------------------------
	// 3. Actors can comment on courses.
	// creado exitosamente
	@Test
	public void testCreateCommentCourses1() {
		authenticate("organiser1");
		int sizeBefore = commentService.findAll().size();
		Comment comment = new Comment();
		comment.setActor(actorService.findByPrincipal());
		comment.setCommentable(courseService.findAll().iterator().next());
		comment.setStars(3);
		comment.setMoment(new Date(System.currentTimeMillis() - 1000));
		comment.setTitle("Create comment course");
		comment.setBody("Comment on Courses");
		commentService.save(comment);
		int sizeAfter = commentService.findAll().size();
		Assert.isTrue(sizeAfter == sizeBefore + 1);
		unauthenticate();
	}

	// ----------------------------------------------------
	// NEGATIVE TEST CASES CREATE CANYON
	// ----------------------------------------------------
	// comment campos en blanco
	@Test(expected = ConstraintViolationException.class)
	public void testCreateCommentCourses2() {
		authenticate("organiser1");

		Comment comment = new Comment();
		comment.setActor(actorService.findByPrincipal());
		comment.setCommentable(courseService.findAll().iterator().next());
		comment.setStars(3);
		comment.setMoment(new Date(System.currentTimeMillis() - 1000));
		comment.setTitle("");
		comment.setBody("");
		commentService.save(comment);

		unauthenticate();
	}

	// actor inexistente
	@Test(expected = IllegalArgumentException.class)
	public void testCreateCommentCourses3() {
		authenticate("none");

		Comment comment = new Comment();
		comment.setActor(actorService.findByPrincipal());
		comment.setCommentable(courseService.findAll().iterator().next());
		comment.setStars(3);
		comment.setMoment(new Date(System.currentTimeMillis() - 1000));
		comment.setTitle("Create comment course");
		comment.setBody("Comment on Courses");
		commentService.save(comment);

		unauthenticate();
	}

}
