package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LearningMaterialService;
import services.ModuleService;
import services.TrainerService;
import domain.LearningMaterial;
import domain.Module;
import domain.Trainer;

@Controller
@RequestMapping("/learningMaterial")
public class LearningMaterialController extends AbstractController {
	// Supporting services ----------------------------------------------------
	@Autowired
	private LearningMaterialService learningMaterialService;

	@Autowired
	private ModuleService moduleService;

	@Autowired
	private TrainerService trainerService;

	// Constructors -----------------------------------------------------------
	public LearningMaterialController() {
		super();
	}

	// List -----------------------------------------------------------
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam int moduleId) {

		ModelAndView result;
		Collection<LearningMaterial> learningMaterials;
		Module module;
		Trainer trainer;
		Boolean mycourse;

		learningMaterials = learningMaterialService
				.learningMaterialsByModule(moduleId);

		result = new ModelAndView("learningMaterial/list");
		result.addObject("learningMaterials", learningMaterials);
		result.addObject("moduleId", moduleId);
		mycourse = false;
		try {
			trainer = trainerService.findByPrincipal();
			module = moduleService.findOne(moduleId);

			mycourse = trainer.equals(module.getCourse().getTrainer());

		} catch (Throwable oops) {

		}

		result.addObject("mycourse", mycourse);

		return result;
	}

	// Display
	// ---------------------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(int learningMaterialId) {
		ModelAndView result;
		LearningMaterial learningMaterial;

		learningMaterial = learningMaterialService.findOne(learningMaterialId);

		result = new ModelAndView("learningMaterial/display");
		result.addObject("learningMaterial", learningMaterial);

		return result;
	}

}
