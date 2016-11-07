package controllers.organiser;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.CordService;
import controllers.AbstractController;
import domain.Cord;

@Controller
@RequestMapping("/cord/organiser")
public class OrganiserCordController extends AbstractController {

	// Supporting services -------------------------------
	@Autowired
	private CordService cordService;

	// Constructors --------------------------------------

	public OrganiserCordController() {
		super();
	}

	// List ----------------------------------------------

	@RequestMapping(value = "/mylist")
	public ModelAndView list() {

		ModelAndView result;
		Collection<Cord> cords;

		cords = cordService.cordsByOrganiserLogged();

		result = new ModelAndView("cord/organiser/mylist");
		result.addObject("cords", cords);
		result.addObject("requestUri", "/cord/organiser/mylist.do");
		return result;
	}

	// Create --------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Cord cord;

		cord = cordService.create();

		result = createEditModelAndView(cord);

		return result;
	}

	// Edit -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int cordId) {
		ModelAndView result;
		Cord cord;
		cord = cordService.findOne(cordId);

		result = createEditModelAndView(cord);
		result.addObject("cord", cord);
		return result;
	}

	// Save --------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Cord cord, BindingResult binding,
			RedirectAttributes redir) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(cord);

		} else {
			try {
				cordService.save(cord);
				result = new ModelAndView("redirect:/cord/organiser/mylist.do");
				result.addObject("requestUri", "/cord/organiser/mylist.do");
				redir.addFlashAttribute("message", "cord.commit.ok");

			} catch (Throwable oops) {
				result = createEditModelAndView(cord, "cord.commit.error");
			}
		}

		return result;
	}

	// Delete --------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Cord cord, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(cord, binding.toString());
		} else {
			try {
				cordService.delete(cord);
				result = new ModelAndView("redirect:/cord/organiser/mylist.do");
				result.addObject("requestUri", "/cord/organiser/mylist.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(cord, "cord.commit.error");
			}
		}
		return result;
	}

	// Ancillary methods
	// --------------------------------------------------------

	protected ModelAndView createEditModelAndView(Cord cord) {
		ModelAndView result;

		result = createEditModelAndView(cord, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Cord cord, String message) {
		ModelAndView result;

		result = new ModelAndView("cord/organiser/edit");
		result.addObject("cord", cord);
		result.addObject("message", message);

		return result;
	}

}
