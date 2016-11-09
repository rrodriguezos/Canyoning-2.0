package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SectionRepository;
import domain.Section;
import domain.Trainer;

@Service
@Transactional
public class SectionService {

	// Managed repository -------------------
	@Autowired
	private SectionRepository sectionRepository;

	// Supporting Services ------------------
	@Autowired
	private TrainerService trainerService;

	// COnstructors -------------------------
	public SectionService() {
		super();
	}

	// Simple CRUD methods--------------------

	public Section create() {
		Section result;

		result = new Section();

		return result;
	}

	public Collection<Section> findAll() {
		Collection<Section> result;

		result = sectionRepository.findAll();

		return result;
	}

	public Section findOne(int sectionId) {
		Section result;

		result = sectionRepository.findOne(sectionId);

		return result;
	}

	public void save(Section section) {
		Assert.notNull(section);
		checkPrincipal(section.getCurriculum().getTrainer());

		sectionRepository.saveAndFlush(section);
	}

	public void delete(Section section) {
		checkPrincipal(section.getCurriculum().getTrainer());

		sectionRepository.delete(section);
	}

	// Other Methods--------------------

	private void checkPrincipal(Trainer u) {
		Trainer trainer;

		trainer = trainerService.findByPrincipal();
		Assert.isTrue(trainer != null);

		Assert.isTrue(trainer.equals(u));
	}

	public Collection<Section> findSectionsByCurriculumId(int tripId) {
		Collection<Section> result;

		result = sectionRepository.sectionsByCurriculum(tripId);
		return result;
	}

}
