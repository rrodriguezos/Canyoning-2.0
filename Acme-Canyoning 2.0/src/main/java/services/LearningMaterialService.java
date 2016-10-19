package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LearningMaterialRepository;
import domain.LearningMaterial;
import domain.Trainer;

@Service
@Transactional
public class LearningMaterialService {
	// Managed repository -------------------
	@Autowired
	private LearningMaterialRepository learningMaterialRepository;

	// Supporting Services ------------------
	@Autowired
	private TrainerService trainerService;

	// COnstructors -------------------------
	public LearningMaterialService() {
		super();
	}

	// Simple CRUD methods--------------------

	public LearningMaterial create() {
		LearningMaterial result;

		result = new LearningMaterial();

		return result;
	}

	public Collection<LearningMaterial> findAll() {
		Collection<LearningMaterial> result;

		result = learningMaterialRepository.findAll();

		return result;
	}

	public LearningMaterial findOne(int learningMaterialId) {
		LearningMaterial result;

		result = learningMaterialRepository.findOne(learningMaterialId);

		return result;
	}

	public void save(LearningMaterial learningMaterial) {
		Assert.notNull(learningMaterial);
		checkPrincipal(learningMaterial.getModule().getCourse().getTrainer());

		learningMaterialRepository.saveAndFlush(learningMaterial);
	}

	public void delete(LearningMaterial learningMaterial) {
		checkPrincipal(learningMaterial.getModule().getCourse().getTrainer());

		learningMaterialRepository.delete(learningMaterial);
	}

	// Other Methods--------------------
	private void checkPrincipal(Trainer t) {
		Trainer trainer;

		trainer = trainerService.findByPrincipal();
		Assert.isTrue(trainer != null);

		Assert.isTrue(trainer.equals(t));
	}

	public Collection<LearningMaterial> learningMaterialsByModule(int moduleId) {
		Collection<LearningMaterial> result;
		result = learningMaterialRepository.learningMaterialsByModule(moduleId);
		return result;
	}

}
