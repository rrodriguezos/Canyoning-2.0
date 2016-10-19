package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "title"),
		@Index(columnList = "description") })
public class LearningMaterial extends DomainEntity {
	
	// Constructor ----------------------------------------------
	public LearningMaterial() {
		super();
	}

	// Attributes -------------------------------------------------
	private String title;
	private String description;
	private String materialLink;
	
	
	
	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@NotBlank
	@URL
	public String getMaterialLink() {
		return materialLink;
	}

	public void setMaterialLink(String materialLink) {
		this.materialLink = materialLink;
	}
	
	// Relationships ---------------------------------------------------
	private Module module;
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
}
