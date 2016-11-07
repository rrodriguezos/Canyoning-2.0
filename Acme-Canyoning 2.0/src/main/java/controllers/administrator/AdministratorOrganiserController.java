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
import controllers.AbstractController;
import domain.Organiser;
import forms.OrganiserForm;
import services.OrganiserService;

@Controller
@RequestMapping("/organiser/administrator")
public class AdministratorOrganiserController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private OrganiserService organiserService;

	// Constructors -----------------------------------------------------------

	public AdministratorOrganiserController() {
		super();
	}

	//Create---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		OrganiserForm organiserForm;

		organiserForm = new OrganiserForm();

		result = new ModelAndView("organiser/create");
		result.addObject("organiserForm", organiserForm);
		return result;
	}

	// Save ----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid OrganiserForm organiserForm,
			BindingResult binding) {
		ModelAndView result;
		Organiser organiser;
		Boolean verificarPass;

		verificarPass = organiserForm.getPassword().equals(
				organiserForm.getConfirmPassword());

		if (binding.hasErrors() || !verificarPass) {
			result = createEditModelAndView(organiserForm);
			if (!verificarPass) {
				result.addObject("message", "organiser.commit.password");
			}
		} else {
			try {
				organiser = organiserService.reconstruct(organiserForm);
				organiserService.save(organiser);
				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				result = createEditModelAndView(organiserForm);
				if (oops instanceof DataIntegrityViolationException) {
					result.addObject("message",
							"organiser.commit.duplicatedUsername");
				} else {
					result.addObject("message", "organiser.commit.error");
				}
			}
		}

		return result;
	}

	// List ------------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Organiser> organisers;

		organisers = organiserService.findAll();

		result = new ModelAndView("organiser/list");
		result.addObject("organisers", organisers);
		result.addObject("requestUri", "organiser/administrator/list.do");

		return result;
	}

	// Display -----------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(int organiserId) {
		ModelAndView result;
		Organiser organiser;

		organiser = organiserService.findOne(organiserId);

		result = new ModelAndView("organiser/display");
		result.addObject("organiser", organiser);

		return result;
	}

	// Ancillary methods--------------------------------------------------------

	protected ModelAndView createEditModelAndView(OrganiserForm organiserForm) {
		ModelAndView result;

		result = createEditModelAndView(organiserForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(OrganiserForm organiserForm,
			String message) {
		ModelAndView result;

		result = new ModelAndView("organiser/create");

		result.addObject("organiserForm", organiserForm);
		result.addObject("message", message);
		return result;
	}
}
