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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "title") })
public class Module extends DomainEntity {

	// Constructor ----------------------------------------------
	public Module() {
		super();
	}

	// Attributes -------------------------------------------------
	private String title;
	private int orderModule;

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Min(0)
	public int getOrderModule() {
		return orderModule;
	}

	public void setOrderModule(int orderModule) {
		this.orderModule = orderModule;
	}

	// Relationships ---------------------------------------------------
	private Course course;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	private Collection<LearningMaterial> learningMaterials;

	@Valid
	@NotNull
	@OneToMany(cascade = { CascadeType.REMOVE, CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.REFRESH }, mappedBy = "module")
	public Collection<LearningMaterial> getLearningMaterials() {
		return learningMaterials;
	}

	public void setLearningMaterials(
			Collection<LearningMaterial> learningMaterials) {
		this.learningMaterials = learningMaterials;
	}

}
