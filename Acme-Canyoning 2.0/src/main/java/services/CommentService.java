package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;
import forms.CommentForm;

@Service
@Transactional
public class CommentService {

	// ManagedRepository ---------------------------
	@Autowired
	private CommentRepository commentRepository;

	// Supporting services -------------------------
	@Autowired
	private CommentableService commentableService;
	@Autowired
	private ActorService actorService;

	// Constructors --------------------------------
	public CommentService() {
		super();
	}

	// Simple CRUD methods -------------------------
	public Comment findOne(int commentId) {
		Assert.isTrue(commentId != 0);
		Comment result = commentRepository.findOne(commentId);
		Assert.notNull(result);
		return result;
	}

	public void save(Comment comment) {
		Assert.notNull(comment);
		commentRepository.saveAndFlush(comment);
	}

	// Other business methods -----------------------
	public Collection<Comment> findCommentsByCommentableId(int commentableId) {
		return commentRepository.findCommentsByCommentableId(commentableId);
	}

	public Comment reconstruct(CommentForm commentForm, int commentableId) {
		Comment result = new Comment();
		result.setActor(actorService.findByPrincipal());
		result.setCommentable(commentableService.findOne(commentableId));
		result.setMoment(new Date(System.currentTimeMillis() - 1000));
		result.setStars(commentForm.getStars());
		result.setBody(commentForm.getBody());
		result.setTitle(commentForm.getTitle());
		return result;
	}

	public Collection<Comment> findAll() {
		Collection<Comment> result;
		result = commentRepository.findAll();
		return result;
	}

}
