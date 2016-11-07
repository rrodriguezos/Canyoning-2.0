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

import services.WetsuitService;
import controllers.AbstractController;
import domain.Wetsuit;

@Controller
@RequestMapping("/wetsuit/organiser")
public class OrganiserWetsuitController extends AbstractController {

	// Supporting services -------------------------------
	@Autowired
	private WetsuitService wetsuitService;

	// Constructors --------------------------------------

	public OrganiserWetsuitController() {
		super();
	}

	// List ----------------------------------------------

	@RequestMapping(value = "/mylist")
	public ModelAndView list() {

		ModelAndView result;
		Collection<Wetsuit> wetsuits;

		wetsuits = wetsuitService.wetsuitsByOrganiserLogged();

		result = new ModelAndView("wetsuit/organiser/mylist");
		result.addObject("wetsuits", wetsuits);
		result.addObject("requestUri", "/wetsuit/organiser/mylist.do");
		return result;
	}

	// Create --------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Wetsuit wetsuit;

		wetsuit = wetsuitService.create();

		result = createEditModelAndView(wetsuit);

		return result;
	}

	// Edit -----------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int wetsuitId) {
		ModelAndView result;
		Wetsuit wetsuit;
		wetsuit = wetsuitService.findOne(wetsuitId);

		result = createEditModelAndView(wetsuit);
		result.addObject("wetsuit", wetsuit);
		return result;
	}

	// Save --------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Wetsuit wetsuit, BindingResult binding,
			RedirectAttributes redir) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(wetsuit);

		} else {
			try {
				wetsuitService.save(wetsuit);
				result = new ModelAndView(
						"redirect:/wetsuit/organiser/mylist.do");
				result.addObject("requestUri", "/wetsuit/organiser/mylist.do");
				redir.addFlashAttribute("message", "wetsuit.commit.ok");

			} catch (Throwable oops) {
				result = createEditModelAndView(wetsuit, "wetsuit.commit.error");
			}
		}

		return result;
	}

	// Delete --------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Wetsuit wetsuit, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(wetsuit, binding.toString());
		} else {
			try {
				wetsuitService.delete(wetsuit);
				result = new ModelAndView(
						"redirect:/wetsuit/organiser/mylist.do");
				result.addObject("requestUri", "/wetsuit/organiser/mylist.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(wetsuit, "wetsuit.commit.error");
			}
		}
		return result;
	}

	// Ancillary methods
	// --------------------------------------------------------

	protected ModelAndView createEditModelAndView(Wetsuit wetsuit) {
		ModelAndView result;

		result = createEditModelAndView(wetsuit, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Wetsuit wetsuit,
			String message) {
		ModelAndView result;

		result = new ModelAndView("wetsuit/organiser/edit");
		result.addObject("wetsuit", wetsuit);
		result.addObject("message", message);

		return result;
	}

}
