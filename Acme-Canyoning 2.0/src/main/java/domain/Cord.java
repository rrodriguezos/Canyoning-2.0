package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Cord extends PieceEquipment {

	// Constructors...................
	public Cord() {
		super();
	}

	// Attributes ----------------------------------------------------------

	private Double length;
	private Double maximumWeight;

	@NotNull
	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	@NotNull
	public Double getMaximumWeight() {
		return maximumWeight;
	}

	public void setMaximumWeight(Double maximumWeight) {
		this.maximumWeight = maximumWeight;
	}

	// Relationships --------------------------------------------------------

}
