package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Administrator extends Actor {

	// Constructors...................

	public Administrator() {
		super();
	}
	
	// Relationships ---------------------------------------------------
	private Collection<Canyon> canyons;
	
	@Valid
	@NotNull
	@OneToMany(mappedBy = "administrator")
	public Collection<Canyon> getCanyons() {
		return canyons;
	}

	public void setCanyons(Collection<Canyon> canyons) {
		this.canyons = canyons;
	}
}
