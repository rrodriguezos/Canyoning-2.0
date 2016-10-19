package forms;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class CommentForm {

	// Constructors -----------------------------
	public CommentForm() {
		super();
	}

	// Attributes -------------------------------
	private String body;
	private int stars;
	private String title;

	@NotBlank
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Range(min = 0, max = 10)
	@NotNull
	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
