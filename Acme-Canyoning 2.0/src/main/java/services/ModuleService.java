package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ModuleRepository;
import domain.LearningMaterial;
import domain.Module;
import domain.Trainer;

@Service
@Transactional
public class ModuleService {

	// Managed repository -------------------
	@Autowired
	private ModuleRepository moduleRepository;

	// Supporting services ------------------
	@Autowired
	private LearningMaterialService learningMaterialService;
	@Autowired
	private TrainerService trainerService;

	// Constructor --------------------------
	public ModuleService() {
		super();
	}

	// Simple CRUD methods ------------------
	public Module create() {
		Module result = new Module();
		result.setLearningMaterials(new ArrayList<LearningMaterial>());
		return result;
	}

	public Collection<Module> findAll() {
		Collection<Module> result = moduleRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Module findOne(int moduleId) {
		Module result = moduleRepository.findOne(moduleId);
		Assert.notNull(result);
		return result;
	}

	public void save(Module module) {
		Assert.notNull(module);
		Trainer admin = trainerService.findByPrincipal();
		Assert.notNull(admin);
		moduleRepository.saveAndFlush(module);
	}

	public void delete(Module module) {
		Assert.notNull(module);
		Trainer trainer = trainerService.findByPrincipal();
		Assert.notNull(trainer);
		for (LearningMaterial l : module.getLearningMaterials()) {
			learningMaterialService.delete(l);
		}
		moduleRepository.delete(module);
	}

	// Other business methods --------------------
	public Collection<Module> findAllByGym(int courseId) {
		Collection<Module> result = moduleRepository
				.findModulesByCourse(courseId);
		Assert.notNull(result);
		return result;
	}

	public Collection<Module> modulesByCourse(int courseId) {
		Collection<Module> result;
		result = moduleRepository.findModulesByCourse(courseId);

		return result;
	}

}
