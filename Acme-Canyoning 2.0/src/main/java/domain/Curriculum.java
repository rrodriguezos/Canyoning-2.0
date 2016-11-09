package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = ("name")) })
public class Curriculum extends DomainEntity {

	public Curriculum() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String name;
	private String email;
	private String phone;
	private Boolean isActive;

	// Getters Setters----------------------------------------------------

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@NotNull
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@SafeHtml(whitelistType = WhiteListType.NONE)
	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Relationships ----------------------------------------------------------

	private Collection<Section> sections;

	@Valid
	@OneToMany(cascade = { CascadeType.REMOVE, CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.REFRESH }, mappedBy = "curriculum")
	public Collection<Section> getSections() {
		return sections;
	}

	public void setSections(Collection<Section> sections) {
		this.sections = sections;
	}

	private Trainer trainer;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

}
