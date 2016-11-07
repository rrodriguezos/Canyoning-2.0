package forms;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import domain.Canyon;
import domain.PieceEquipment;

public class ActivityForm {

	private String title;
	private String description;
	private int numberSeats;
	private Date moment;
	private int seatsAvailable;

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

	@Min(0)
	public int getNumberSeats() {
		return numberSeats;
	}

	public void setNumberSeats(int numberSeats) {
		this.numberSeats = numberSeats;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Future
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@Min(0)
	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	private Collection<PieceEquipment> piecesEquipments;

	public Collection<PieceEquipment> getPieceEquipments() {
		return piecesEquipments;
	}

	public void setPieceEquipments(Collection<PieceEquipment> piecesEquipments) {
		this.piecesEquipments = piecesEquipments;
	}

	private Canyon canyon;

	public Canyon getCanyon() {
		return canyon;
	}

	public void setCanyon(Canyon canyon) {
		this.canyon = canyon;
	}

}
