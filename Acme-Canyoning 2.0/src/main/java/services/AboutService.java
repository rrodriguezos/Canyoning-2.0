package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AboutRepository;
import security.Authority;
import domain.About;
import domain.Actor;
import domain.Organiser;

@Service
@Transactional
public class AboutService {

	// Managed repository -------------------
	@Autowired
	private AboutRepository aboutRepository;

	// Supporting Services ------------------
	@Autowired
	private OrganiserService organiserService;

	@Autowired
	private ActorService actorService;

	// COnstructors -------------------------
	public AboutService() {
		super();
	}

	// Simple CRUD methods--------------------

	public void save(About about) {
		Assert.notNull(about);
		checkPrincipalOrganiser();
		aboutRepository.saveAndFlush(about);
	}

	public About findOne(int aboutId) {
		checkPrincipalOrganiser();
		About about;
		about = aboutRepository.findOne(aboutId);
		return about;
	}

	public About create() {
		About result;
		Organiser organiser;
		
		organiser = organiserService.findByPrincipal();
		result = new About();
		result.setOrganiser(organiser);
		checkPrincipalOrganiser();
		
		return result;
	}

	public About findAboutByOrganiser() {
		About result;
		Organiser organiser;
		organiser = organiserService.findByPrincipal();
		checkPrincipalOrganiser();
		
		result = aboutRepository.findAboutByOrganiser(organiser.getId());		
		
		return result;
	}

	private void checkPrincipalOrganiser() {
		Actor actor;
		Authority authority;

		actor = actorService.findByPrincipal();
		Assert.isTrue(actor != null);

		authority = new Authority();
		authority.setAuthority("ORGANISER");

		Assert.isTrue(actor.getUserAccount().getAuthorities()
				.contains(authority));
	}

}
