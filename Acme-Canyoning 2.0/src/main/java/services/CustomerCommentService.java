package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerCommentRepository;
import domain.CustomerComment;

@Service
@Transactional
public class CustomerCommentService {
	// Managed Repository ------------------------------
	@Autowired
	private CustomerCommentRepository customerCommentRepository;

	// Supporting Services -----------------------------

	// Constructors ------------------------------------
	public CustomerCommentService() {
		super();
	}

	// Simple CRUD Methods -----------------------------

	public Collection<CustomerComment> findAll() {
		Collection<CustomerComment> result = customerCommentRepository
				.findAll();
		Assert.notNull(result);
		return result;
	}

}
