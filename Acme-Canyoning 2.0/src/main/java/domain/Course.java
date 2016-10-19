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

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "title"),
		@Index(columnList = "description")})
public class Course extends Commentable {

	// Constructor ----------------------------------------------
	public Course() {
		super();
	}

	// Attributes -------------------------------------------------
	private String title;
	private String description;
	private String banner;

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
	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	// Relationships ---------------------------------------------------
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
	private Collection<Module> modules;
	
	@NotNull
	@Valid
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	public Collection<Module> getModules() {
		return modules;
	}
	public void setModules(Collection<Module> modules) {
		this.modules = modules;
	}
}
