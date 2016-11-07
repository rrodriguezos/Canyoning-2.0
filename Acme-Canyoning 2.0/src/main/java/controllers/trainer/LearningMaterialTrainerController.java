package controllers.trainer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.LearningMaterialService;
import services.ModuleService;
import controllers.AbstractController;
import domain.LearningMaterial;
import domain.Module;

@Controller
@RequestMapping("/learningMaterial/trainer")
public class LearningMaterialTrainerController extends AbstractController {

	// Supporting services -------------------------------

	@Autowired
	private LearningMaterialService learningMaterialService;
	@Autowired
	private ModuleService moduleService;

	// Constructors --------------------------------------

	public LearningMaterialTrainerController() {
		super();
	}

	// Create ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int moduleId) {
		ModelAndView result;
		LearningMaterial learningMaterial;
		Module module;

		learningMaterial = learningMaterialService.create();
		module = moduleService.findOne(moduleId);
		learningMaterial.setModule(module);

		result = createEditModelAndView(learningMaterial);

		return result;
	}

	// Save ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid LearningMaterial learningMaterial,
			BindingResult binding, RedirectAttributes redir) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(learningMaterial);

		} else {
			try {
				learningMaterialService.save(learningMaterial);
				result = new ModelAndView(
						"redirect:/learningMaterial/list.do?moduleId="
								+ learningMaterial.getModule().getId());
				redir.addFlashAttribute("message",
						"learningMaterial.commit.ok");

			} catch (Throwable oops) {
				result = createEditModelAndView(learningMaterial);
				result.addObject("message", "learningMaterial.commit.error");
			}
		}

		return result;
	}

	// Delete-------------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int learningMaterialId) {
		ModelAndView result;

		LearningMaterial learningMaterial = learningMaterialService
				.findOne(learningMaterialId);

		learningMaterialService.delete(learningMaterial);

		result = new ModelAndView("redirect:/course/trainer/mylist.do");
		result.addObject("requestUri", "/course/trainer/mylist.do");

		return result;
	}

	// Ancillary
	// methods---------------------------------------------------------------

	protected ModelAndView createEditModelAndView(
			LearningMaterial learningMaterial) {
		ModelAndView result;

		result = createEditModelAndView(learningMaterial, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			LearningMaterial learningMaterial, String message) {
		ModelAndView result;

		result = new ModelAndView("learningMaterial/create");

		result.addObject("learningMaterial", learningMaterial);
		result.addObject("message", message);

		return result;
	}

}
