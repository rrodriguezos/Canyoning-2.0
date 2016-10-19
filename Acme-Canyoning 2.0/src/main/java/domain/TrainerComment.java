package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;


@Entity
@Access(AccessType.PROPERTY)
public class TrainerComment extends Commentable {
	
	public TrainerComment() {
		super();
	}

	private Trainer trainer;

	@Valid
	@OneToOne(optional = true)
	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer customer) {
		this.trainer = customer;
	}

}
