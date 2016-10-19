package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.Range;

@Embeddable
@Access(AccessType.PROPERTY)
public class GPSCoordinates {
	// Attributes --------------------------------------------------------------
	private Double latitude;
	private Double longitude;
	private Double altitude;

	// Getters&Setters ---------------------------------------------------------
	@Digits(integer = 2, fraction = 2)
	@Range(min = -90, max = 90)
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Range(min = -180, max = 180)
	@Digits(integer = 3, fraction = 3)
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}
}
