package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "moment"),
		@Index(columnList = "numberSeats"), @Index(columnList = "title"),
		@Index(columnList = "description") })
public class Activity extends Commentable {

	// Constructor ----------------------------------------------
	public Activity() {
		super();
	}

	// Attributes -------------------------------------------------
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

	// Relationships ---------------------------------------------------
	private Organiser organiser;
	private Canyon canyon;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Canyon getCanyon() {
		return canyon;
	}

	public void setCanyon(Canyon canyon) {
		this.canyon = canyon;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Organiser getOrganiser() {
		return organiser;
	}

	public void setOrganiser(Organiser organiser) {
		this.organiser = organiser;
	}

	private Collection<Request> requests;

	@Valid
	@OneToMany(cascade=CascadeType.ALL,mappedBy = "activity")
	public Collection<Request> getRequests() {
		return requests;
	}

	public void setRequests(Collection<Request> requests) {
		this.requests = requests;
	}

}
