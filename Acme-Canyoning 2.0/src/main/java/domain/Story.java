package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "title"),
		@Index(columnList = "authorName"), @Index(columnList = "text") })
public class Story extends DomainEntity {

	// Constructor ----------------------------------------------
	public Story() {
		super();
	}

	// Attributes -------------------------------------------------
	private String title;
	private String authorName;
	private Collection<String> resourcesList;
	private String text;

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@ElementCollection
	// @URL
	public Collection<String> getResourcesList() {
		return resourcesList;
	}

	public void setResourcesList(Collection<String> resourcesList) {
		this.resourcesList = resourcesList;
	}

	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	// Relationships ---------------------------------------------------
	private Canyon canyon;
	private Administrator administrator;

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
	@ManyToOne(optional = false)
	public Canyon getCanyon() {
		return canyon;
	}

	public void setCanyon(Canyon canyon) {
		this.canyon = canyon;
	}

}
