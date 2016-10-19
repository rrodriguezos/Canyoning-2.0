package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class CustomerComment extends Commentable {

	public CustomerComment() {
		super();
	}

	private Customer customer;

	@Valid
	@OneToOne(optional = true)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
