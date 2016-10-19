package services;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.OrganiserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Activity;
import domain.Administrator;
import domain.Comment;
import domain.Customer;
import domain.Organiser;
import forms.OrganiserForm;

@Service
@Transactional
public class OrganiserService {

	// Managed repository -------------------------

	@Autowired
	private OrganiserRepository organiserRepository;

	// Supporting Services -------------------------

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private UserAccountService userAccountService;

	// Constructors -------------------------------
	public OrganiserService() {
		super();
	}

	// Simple CRUD methods -----------------------------

	public Organiser create() {
		checkPrincipalAdministrator();

		UserAccount useraccount;
		Organiser result;
		Collection<Comment> comments;
		Collection<Activity> activities;

		Authority aut = new Authority();

		aut.setAuthority("ORGANISER");
		useraccount = userAccountService.create();

		result = new Organiser();

		useraccount.addAuthority(aut);
		result.setUserAccount(useraccount);

		activities = new LinkedList<Activity>();
		result.setActivities(activities);

		comments = new LinkedList<Comment>();
		result.setComments(comments);

		return result;
	}
	
	public Collection<Organiser> findAll() {

		Collection<Organiser> result;

		result = organiserRepository.findAll();

		return result;
	}

	public Organiser findOne(int organiserId) {
		Organiser result;

		result = organiserRepository.findOne(organiserId);

		return result;
	}

	public void save(Organiser organiser) {

		Boolean create;
		create = false;
		if (organiser.getId() == 0) {
			Md5PasswordEncoder encoder;

			create = true;
			encoder = new Md5PasswordEncoder();

			organiser.getUserAccount().setPassword(
					encoder.encodePassword(organiser.getUserAccount()
							.getPassword(), null));
		}
		organiser = organiserRepository.saveAndFlush(organiser);
		Assert.notNull(organiser);
	}

	private void checkPrincipalAdministrator() {
		Administrator administrator;
		Authority authority;

		administrator = administratorService.findByPrincipal();
		Assert.isTrue(administrator != null);
		authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(administrator.getUserAccount().getAuthorities()
				.contains(authority));
	}
	
	// other methods
		// -------------------------------------------------------------------------

		public Organiser findByPrincipal() {
			UserAccount userAccount;
			Organiser result;
			int id;

			userAccount = LoginService.getPrincipal();
			Assert.notNull(userAccount);
			id = userAccount.getId();
			result = organiserRepository.findByUserAccountId(id);
			Assert.notNull(result);

			return result;

		}

		public Organiser reconstruct(OrganiserForm organiserForm) {
			Organiser res;
			res = create();
			Assert.isTrue(organiserForm.getPassword().equals(
					organiserForm.getConfirmPassword()));

			res.setPhone(organiserForm.getPhone());
			res.setEmail(organiserForm.getEmail());

			res.getUserAccount().setUsername(organiserForm.getUsername());
			res.getUserAccount().setPassword(organiserForm.getPassword());

			return res;
		}
		public Organiser findByUserAccount(UserAccount userAccount) {
			Assert.notNull(userAccount);
			Organiser result;
			result = organiserRepository.findByUserAccountId(userAccount.getId());
			return result;
		}
}
