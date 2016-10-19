package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class OrganiserComment extends Commentable {

	public OrganiserComment() {
		super();
	}
	
	private Organiser organiser;

	@Valid
	@OneToOne(optional=true)
	public Organiser getOrganiser() {
		return organiser;
	}
	public void setOrganiser(Organiser organiser) {
		this.organiser = organiser;
	}
	
	
	
}
