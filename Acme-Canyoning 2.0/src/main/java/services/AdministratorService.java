package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	// Managed repository -------------------------

	@Autowired
	private AdministratorRepository administratorRepository;

	// Constructors -------------------------------
	public AdministratorService() {
		super();
	}

	// Simple CRUD methods -----------------------------
	public Collection<Administrator> findAll() {
		Collection<Administrator> result;

		result = administratorRepository.findAll();

		return result;
	}

	public Administrator findOne(int administratorId) {
		Administrator result;

		result = administratorRepository.findOne(administratorId);

		return result;
	}

	// Other bussiness
	// methods-----------------------------------------------------

	public Administrator findByPrincipal() {
		Administrator result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Administrator findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);

		Administrator result;

		result = administratorRepository.findByUserAccountId(userAccount
				.getId());

		return result;
	}

	private void checkPrincipalAdministrator() {
		Administrator administrator;
		Authority authority;

		administrator = findByPrincipal();
		Assert.isTrue(administrator != null);
		authority = new Authority();
		authority.setAuthority("ADMINISTRATOR");

		Assert.isTrue(administrator.getUserAccount().getAuthorities()
				.contains(authority));
	}

	public void isAdmin(UserAccount account) {
		Collection<Authority> authorities = account.getAuthorities();
		Boolean res = false;
		for (Authority a : authorities) {
			if (a.getAuthority().equals("ADMINISTRATOR"))
				res = true;
		}
		Assert.isTrue(res);
	}

}
