package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.OrganiserCommentRepository;
import domain.OrganiserComment;

@Service
@Transactional
public class OrganiserCommentService {

	// Managed Repository ------------------------------
	@Autowired
	private OrganiserCommentRepository organiserCommentRepository;

	// Supporting Services -----------------------------

	// Constructors ------------------------------------
	public OrganiserCommentService() {
		super();
	}

	// Simple CRUD Methods -----------------------------

	public Collection<OrganiserComment> findAll() {
		Collection<OrganiserComment> result = organiserCommentRepository
				.findAll();
		Assert.notNull(result);
		return result;
	}

}
