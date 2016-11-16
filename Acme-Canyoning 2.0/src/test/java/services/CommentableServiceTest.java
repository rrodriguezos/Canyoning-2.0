package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Activity;
import domain.Canyon;
import domain.Commentable;
import domain.OrganiserComment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class CommentableServiceTest extends AbstractTest {

	// Service under test ------------------
	@Autowired
	private CommentableService commentableService;

	// Supporting services -----------------
	@Autowired
	private CanyonService canyonService;
	@Autowired
	private ActivityService activityService;
	@Autowired
	private OrganiserCommentService organiserCommentService;

	// Positive Tests -------------------------------
	/**
	 * Comentable devuelve un Canyon cuando se busca un Commentable por el id de
	 * un canyon
	 */
	@Test
	public void testFindOneByCanyonId() {
		Canyon canyon = canyonService.findAll().iterator().next();
		Commentable commentable = commentableService.findOne(canyon.getId());
		Assert.isTrue(commentable instanceof Canyon);
	}

	/**
	 * Comentable devuelve una Activity cuando se busca un Commentable por el id
	 * de un Service
	 */
	@Test
	public void testFindOneByActivityId() {
		Activity activity = activityService.findAll().iterator().next();
		Commentable commentable = commentableService.findOne(activity.getId());
		Assert.isTrue(commentable instanceof Activity);
	}

	/**
	 * Comentable devuelve una Activity cuando se busca un Commentable por el id
	 * de un Service
	 */
	@Test
	public void testFindOneByTrainerId() {
		OrganiserComment organiserComment = organiserCommentService.findAll()
				.iterator().next();
		Commentable commentable = commentableService.findOne(organiserComment
				.getId());
		Assert.isTrue(commentable instanceof OrganiserComment);
	}

}
