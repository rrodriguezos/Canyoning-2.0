package forms;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;


@Embeddable
@Access(AccessType.PROPERTY)
public class CanyonEditForm {
	private int canyonId;
	private String name;
	private String description;
	private Collection<String> pictures;
	private Integer difficultyLevel;
	private String route;
	private double latitude;
	private double longitude;
	private double altitude;
	
	
	public int getCanyonId() {
		return canyonId;
	}
	public void setCanyonId(int canyonId) {
		this.canyonId = canyonId;
	}
	@NotBlank
	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ElementCollection
	public Collection<String> getPictures() {
		return pictures;
	}

	public void setPictures(Collection<String> pictures) {
		this.pictures = pictures;
	}

	@Range(min = 0, max = 10)
	@NotNull
	public Integer getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(Integer difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}
	
	@Digits(integer=2,fraction=2)
	@Range(min=-90,max=90)
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	@Range(min=-180,max=180)
	@Digits(integer=3,fraction=3)
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
}
