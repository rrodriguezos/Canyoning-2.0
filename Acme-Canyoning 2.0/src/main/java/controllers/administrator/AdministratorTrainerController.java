package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.TrainerService;
import controllers.AbstractController;
import domain.Trainer;
import forms.TrainerForm;

@Controller
@RequestMapping("/trainer/administrator")
public class AdministratorTrainerController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private TrainerService trainerService;

	// Constructors -----------------------------------------------------------

	public AdministratorTrainerController() {
		super();
	}

	// Create---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		TrainerForm trainerForm;

		trainerForm = new TrainerForm();

		result = new ModelAndView("trainer/create");
		result.addObject("trainerForm", trainerForm);
		return result;
	}

	// Save ----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid TrainerForm trainerForm,
			BindingResult binding) {
		ModelAndView result;
		Trainer trainer;
		Boolean verificarPass;

		verificarPass = trainerForm.getPassword().equals(
				trainerForm.getConfirmPassword());

		if (binding.hasErrors() || !verificarPass) {
			result = createEditModelAndView(trainerForm);
			if (!verificarPass) {
				result.addObject("message", "trainer.commit.password");
			}
		} else {
			try {
				trainer = trainerService.reconstruct(trainerForm);
				trainerService.save(trainer);
				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				result = createEditModelAndView(trainerForm);
				if (oops instanceof DataIntegrityViolationException) {
					result.addObject("message",
							"trainer.commit.duplicatedUsername");
				} else {
					result.addObject("message", "trainer.commit.error");
				}
			}
		}

		return result;
	}

	// List ------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Trainer> trainers;

		trainers = trainerService.findAll();

		result = new ModelAndView("trainer/list");
		result.addObject("trainers", trainers);
		result.addObject("requestUri", "trainer/administrator/list.do");

		return result;
	}

	// Display -----------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(int trainerId) {
		ModelAndView result;
		Trainer trainer;

		trainer = trainerService.findOne(trainerId);

		result = new ModelAndView("trainer/display");
		result.addObject("trainer", trainer);

		return result;
	}

	// Ancillary methods--------------------------------------------------------

	protected ModelAndView createEditModelAndView(TrainerForm trainerForm) {
		ModelAndView result;

		result = createEditModelAndView(trainerForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(TrainerForm trainerForm,
			String message) {
		ModelAndView result;

		result = new ModelAndView("trainer/create");

		result.addObject("trainerForm", trainerForm);
		result.addObject("message", message);
		return result;
	}

}
