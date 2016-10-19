package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	// Constructors -----------------------------------------------------------
	public Customer() {
		super();
	}

	// Relationships ---------------------------------------------------
	private CustomerComment customerComment;

	@Valid
	@OneToOne(optional = true, cascade = CascadeType.ALL)
	public CustomerComment getCustomerComment() {
		return customerComment;
	}

	public void setCustomerComment(CustomerComment customerComment) {
		this.customerComment = customerComment;
	}

	private Collection<Request> requests;

	@Valid
	@NotNull
	@OneToMany(mappedBy = "customer")
	public Collection<Request> getRequests() {
		return requests;
	}

	public void setRequests(Collection<Request> requests) {
		this.requests = requests;
	}
}
