package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Request extends DomainEntity {

	// Constructor ----------------------------------------------
	public Request() {
		super();
	}

	// Attributes -------------------------------------------------

	public enum RequestState {
		PENDING, ACCEPTED, REJECTED;
	}

	private Date momentPending;
	private Date momentAccepted;
	private RequestState requestState;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	public Date getMomentPending() {
		return momentPending;
	}

	public void setMomentPending(Date momentPending) {
		this.momentPending = momentPending;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	public Date getMomentAccepted() {
		return momentAccepted;
	}

	public void setMomentAccepted(Date momentAccepted) {
		this.momentAccepted = momentAccepted;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	public RequestState getRequestState() {
		return requestState;
	}

	public void setRequestState(RequestState requestState) {
		this.requestState = requestState;
	}

	// Relationships ---------------------------------------------------

	private Customer customer;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	private Activity activity;
	
	@Valid
	@ManyToOne(optional=true)
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

}
