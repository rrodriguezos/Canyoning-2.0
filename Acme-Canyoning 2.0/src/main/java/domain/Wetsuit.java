package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Wetsuit extends PieceEquipment {

	// Constructors...................
	public Wetsuit() {
		super();
	}

	// Attributes ----------------------------------------------------------
	// añadir nota de que los grados vienen en celsius
	private double minimumTemperature;
	private boolean trousers;
	private String sizeSleeves;

	// Values ------------------------------------------------------------------
	public static final String LONG = "LONG";
	public static final String SHORT = "SHORT";

	@NotNull
	public double getMinimumTemperature() {
		return minimumTemperature;
	}

	public void setMinimumTemperature(double minimumTemperature) {
		this.minimumTemperature = minimumTemperature;
	}

	@NotNull
	public boolean getTrousers() {
		return trousers;
	}

	public void setTrousers(boolean trousers) {
		this.trousers = trousers;
	}

	// Añadir nota al UML y conceptual en la que decir que solo puede ser larga
	// o corta (long or short) EL PATTERN
	@NotBlank
	@Pattern(regexp = "^" + LONG + "|" + SHORT + "$")
	public String getSizeSleeves() {
		return sizeSleeves;
	}

	public void setSizeSleeves(String sizeSleeves) {
		this.sizeSleeves = sizeSleeves;
	}

	// Realations---------------------------------------------------------------------

}
