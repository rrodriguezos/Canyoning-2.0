package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "message") })
public class About extends DomainEntity {
	// Constructors...................

	public About() {
		super();
	}

	// Attributes ----------------------------------------------------------

	private String message;

	@NotBlank
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	// Relationships --------------------------------------------------------
	private Organiser organiser;

	
	
	@Valid
	@NotNull
	@OneToOne(optional = false)
	public Organiser getOrganiser() {
		return organiser;
	}
	public void setOrganiser(Organiser organiser) {
		this.organiser = organiser;
	}
}
