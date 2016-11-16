package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StoryRepository;
import domain.Administrator;
import domain.Story;

@Service
@Transactional
public class StoryService {

	// Managed repository -------------------
	@Autowired
	private StoryRepository storyRepository;

	// Supporting Services ------------------
	@Autowired
	private AdministratorService administratorService;

	// COnstructors -------------------------
	public StoryService() {
		super();
	}

	// Simple CRUD methods--------------------

	public Story create() {
		Story result;
		Administrator admin;
		admin = administratorService.findByPrincipal();
		checkPrincipal(admin);
		Collection<String> resourcesList;

		result = new Story();
		result.setAdministrator(admin);

		resourcesList = new ArrayList<String>();
		result.setResourcesList(resourcesList);

		return result;
	}

	public Collection<Story> findAll() {
		Collection<Story> result;

		result = storyRepository.findAll();

		return result;
	}

	public Story findOne(int storyId) {
		Story result;

		result = storyRepository.findOne(storyId);

		return result;
	}

	public void save(Story story) {
		Assert.notNull(story);
		checkPrincipal(story.getCanyon().getAdministrator());

		storyRepository.saveAndFlush(story);
	}

	public void delete(Story story) {
		checkPrincipal(story.getCanyon().getAdministrator());

		storyRepository.delete(story);
	}

	// Other Methods--------------------

	private void checkPrincipal(Administrator u) {
		Administrator administrator;

		administrator = administratorService.findByPrincipal();
		Assert.isTrue(administrator != null);

		Assert.isTrue(administrator.equals(u));
	}

	public Collection<Story> storiesByCanyon(int canyonId) {
		Collection<Story> result;

		result = storyRepository.storiesByCanyon(canyonId);
		return result;
	}

	public Collection<Story> findStoriesByAdministrator() {
		Collection<Story> result;
		Administrator administrator;

		administrator = administratorService.findByPrincipal();

		result = storyRepository.findStoryByAdministrator(administrator
				.getUserAccount().getId());

		return result;
	}

	public Double avgStoriesPerCanyon() {
		Double result;

		result = storyRepository.avgStoriesPerCanyon();

		return result;
	}

	public Double minStoriesPerCanyon() {
		Double result;

		result = storyRepository.minStoriesPerCanyon();

		return result;
	}

	public Double maxStoriesPerCanyon() {
		Double result;

		result = storyRepository.maxStoriesPerCanyon();

		return result;
	}

}
