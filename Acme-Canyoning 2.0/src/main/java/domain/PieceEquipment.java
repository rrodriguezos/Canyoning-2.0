package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public abstract class PieceEquipment extends DomainEntity {

	private String name;
	private String description;
	private String make;
	private String model;

	// Constructors------------------------------------------------

	public PieceEquipment() {
		super();
	}

	// Getters and Setters---------------------------------------------------

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@NotBlank
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	@NotBlank
	@URL
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	// Relationships ----------------------------------------------------------

	private Organiser organiser;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Organiser getOrganiser() {
		return organiser;
	}

	public void setOrganiser(Organiser organiser) {
		this.organiser = organiser;
	}

}
