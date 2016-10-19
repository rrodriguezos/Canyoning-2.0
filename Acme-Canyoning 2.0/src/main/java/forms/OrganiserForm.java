package forms;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

public class OrganiserForm {

	private String password;
	private String confirmPassword;
	private String username;
	private String phone;
	private String email;

	// Constructors...................

	public OrganiserForm() {
		super();
	}

	// Getters and Setters........

	@SafeHtml(whitelistType=WhiteListType.NONE)
	@NotBlank
	@Size(min = 5, max = 32)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@SafeHtml(whitelistType=WhiteListType.NONE)
	@NotBlank
	@Size(min = 5, max = 32)
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	

	@SafeHtml(whitelistType=WhiteListType.NONE)
	@NotBlank
	@Size(min = 5, max = 32)
	@Column(unique = true)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	@SafeHtml(whitelistType=WhiteListType.NONE)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@SafeHtml(whitelistType=WhiteListType.NONE)
	@NotBlank
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
