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

	// Constructor --------------------------------------------------
	public SectionService() {
		super();
	}

	// Managed Repository -------------------------------------------
	@Autowired
	private SectionRepository sectionRepository;

	// Supported Services -------------------------------------------

	@Autowired
	private TrainerService trainerService;

	// CRUD methods -------------------------------------------------
	public Collection<Section> findAll() {
		Collection<Section> result;

		result = sectionRepository.findAll();

		return result;
	}

	public Section findOne(int sectionId) {
		Section result;

		Assert.isTrue(sectionId != 0);

		result = sectionRepository.findOne(sectionId);
		Assert.notNull(result);

		return result;
	}

	public Section create() {
		Section result;

		result = new Section();

		return result;
	}

	public void save(Section section) {
		Assert.notNull(section);
		sectionRepository.saveAndFlush(section);
	}

	public Collection<Section> sectionsByCurriculum(int curriculumrId) {
		Collection<Section> result;
		result = sectionRepository.sectionsByCurriculum(curriculumrId);

		return result;
	}

	public void delete(Section section) {
		checkPrincipal(section.getCurriculum().getTrainer());

		sectionRepository.delete(section);
	}

	// Other Business Methods ---------------------------------------

	private void checkPrincipal(Trainer u) {
		Trainer trainer;

		trainer = trainerService.findByPrincipal();
		Assert.isTrue(trainer != null);

		Assert.isTrue(trainer.equals(u));
	}
}