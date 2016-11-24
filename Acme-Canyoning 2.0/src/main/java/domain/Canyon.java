package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "difficultyLevel"),
		@Index(columnList = "route"), @Index(columnList = "name"),
		@Index(columnList = "description") })
public class Canyon extends Commentable {

	// Constructor ----------------------------------------------
	public Canyon() {
		super();
	}

	// Attributes -------------------------------------------------
	private String name;
	private String description;
	private Collection<String> pictures;
	private Integer difficultyLevel;
	private String route;
	private GPSCoordinates gpsCoordinates;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
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
	@URL
	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	@Valid
	@NotNull
	@AttributeOverrides({
			@AttributeOverride(name = "longitude", column = @Column(name = "longitude")),
			@AttributeOverride(name = "latitude", column = @Column(name = "latitude")),
			@AttributeOverride(name = "altitude", column = @Column(name = "altitude")) })
	public GPSCoordinates getGpsCoordinates() {
		return gpsCoordinates;
	}

	public void setGpsCoordinates(GPSCoordinates gpsCoordinates) {
		this.gpsCoordinates = gpsCoordinates;
	}

	// Relationships ---------------------------------------------------
	private Administrator administrator;
	private Collection<Activity> activities;
private Collection<Story> stories;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	@Valid
	@NotNull
	@OneToMany(cascade=CascadeType.ALL,mappedBy = "canyon")
	public Collection<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Collection<Activity> activities) {
		this.activities = activities;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "canyon")
	public Collection<Story> getStories() {
		return stories;
	}

	public void setStories(Collection<Story> stories) {
		this.stories = stories;
	}

}
