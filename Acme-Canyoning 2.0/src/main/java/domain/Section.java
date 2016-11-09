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

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "title"), @Index(columnList = "content") })
public class Section extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Section() {
		super();

	}

	// Attributes -------------------------------------------------------------

	private String title;

	private String content;

	private Collection<String> attachments;

	@NotBlank
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ElementCollection
	public Collection<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(Collection<String> attachments) {
		this.attachments = attachments;
	}

	// Relationships ----------------------------------------------------------
	private Curriculum curriculum;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Curriculum getCurriculum() {
		return curriculum;
	}

	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}
}