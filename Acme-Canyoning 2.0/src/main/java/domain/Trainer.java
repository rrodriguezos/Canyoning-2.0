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
public class Trainer extends Actor {

	// Constructors -----------------------------------------------------------
	public Trainer() {
		super();
	}

	// Relationships ---------------------------------------------------
	private TrainerComment trainerComment;

	@Valid
	@OneToOne(optional = true, cascade = CascadeType.ALL)
	public TrainerComment getTrainerComment() {
		return trainerComment;
	}

	public void setTrainerComment(TrainerComment trainerComment) {
		this.trainerComment = trainerComment;
	}
	
	private Collection<Course> courses;
	
	@Valid
	@NotNull
	@OneToMany(mappedBy = "trainer")
	public Collection<Course> getCourses() {
		return courses;
	}

	public void setCourses(Collection<Course> courses) {
		this.courses = courses;
	}

}
