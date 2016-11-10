package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Curriculum;
import domain.Section;
import domain.Trainer;

@Service
@Transactional
public class CurriculumService {
	// Managed repository -------------------
	@Autowired
	private CurriculumRepository curriculumRepository;

	// Supporting Services ------------------
	@Autowired
	private TrainerService trainerService;

	// COnstructors -------------------------------------------------------
	public CurriculumService() {
		super();
	}

	// Simple CRUD methods--------------------------------------------------

	public Curriculum create() {
		Curriculum result;

		Trainer trainer;

		result = new Curriculum();
		result.setSections(new ArrayList<Section>());

		result.setIsActive(false);

		trainer = trainerService.findByPrincipal();
		result.setTrainer(trainer);

		return result;
	}

	public Collection<Curriculum> findAll() {
		Collection<Curriculum> result;

		result = curriculumRepository.findAll();

		return result;
	}

	public Curriculum findOne(int curriculumId) {
		Curriculum result;

		result = curriculumRepository.findOne(curriculumId);

		return result;
	}

	public void save(Curriculum curriculum) {
		Assert.notNull(curriculum);
		Trainer trainer;
		trainer = curriculum.getTrainer();
		checkPrincipal(trainer);
		curriculum.setLastUpdate(new Date(System.currentTimeMillis() - 1000));
		curriculum.setIsActive(false);
		

		curriculumRepository.saveAndFlush(curriculum);
	}

	public void saveEdit(Curriculum curriculum) {
		Assert.notNull(curriculum);
		Trainer trainer;
		trainer = curriculum.getTrainer();
		checkPrincipal(trainer);
		curriculum.setLastUpdate(new Date(System.currentTimeMillis() - 1000));
		curriculumRepository.saveAndFlush(curriculum);
	}

	public void delete(Curriculum curriculum) {
		Assert.notNull(curriculum);
		checkPrincipal(curriculum.getTrainer());

		curriculumRepository.delete(curriculum);
	}

	// Other Methods--------------------
	private void checkPrincipal(Trainer u) {
		Trainer trainer;

		trainer = trainerService.findByPrincipal();
		Assert.isTrue(trainer != null);

		Assert.isTrue(trainer.equals(u));
	}

	public Collection<Curriculum> curriculumByTrainerLogged() {
		Collection<Curriculum> result;
		Trainer trainer;

		trainer = trainerService.findByPrincipal();

		result = curriculumRepository.findCurriculumsByTrainer(trainer.getId());

		return result;
	}

	public void changeStateCVtoActive(Curriculum cv) {
		Assert.notNull(cv);
		checkPrincipal(cv.getTrainer());
		Collection<Curriculum> curriculumsByTrainer;

		curriculumsByTrainer = curriculumByTrainerLogged();
		for (Curriculum c : curriculumsByTrainer) {
			if (!c.equals(cv)) {
				c.setIsActive(false);
			}

		}
		cv.setIsActive(true);

	}

	public Curriculum curriculumActiveByTrainer(Integer trainerId) {
		Curriculum result;

		result = curriculumRepository.curriculumActiveByTrainer(trainerId);

		return result;

	}

	public Double averageCurriculumsByTrainer() {
		Double result;
		result = curriculumRepository.averageCurriculumsByTrainer();
		return result;
	}

}
